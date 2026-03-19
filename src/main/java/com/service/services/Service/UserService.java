package com.service.services.Service;

import com.service.services.Entity.Category;
import com.service.services.Entity.Location;
import com.service.services.Entity.ServiceProviders;
import com.service.services.Exception.PhoneAlreadyExist;
import com.service.services.Exception.PhoneNotExist;
import com.service.services.Repository.CategoryRepo;
import com.service.services.Repository.ServiceProvidersRepo;
import com.service.services.RequestDtos.RegisterRequestDto;
import com.service.services.ResponseDtos.ProfileResponseDto;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    @Autowired
    ServiceProvidersRepo serviceProvidersRepo;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    LocationService locationService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ServiceProvidersService serviceProvidersService;
    private static final String CACHE_NAME = "phone";
    private static final String CACHE_NAME2 = "profile";
    private static final String CACHE_NAME3 = "id";

    @Transactional
    public ServiceProviders createUser(RegisterRequestDto registerRequestDto) {
        log.info("find the service provider exist or not "); //need cache
        Optional<ServiceProviders> getProviders = serviceProvidersRepo.findByPhone(registerRequestDto.getPhone());
        if (getProviders.isPresent()) {
            throw new PhoneAlreadyExist("Phone number already Register");
        }
        List<String> location=registerRequestDto.getLocation();
        String state   = location.get(0);
        String district = location.get(1);
        String block   = location.get(2);
        String village = location.get(3);
       Location location1= locationService.findLocation(state,district,block,village);

        log.info("find the category to get injected to the actual Entity");
        List<Category> categoryList = categoryService.categoryListByIds(registerRequestDto.getCategories_ids());
        log.info("mapping to the actual Entity");
        ServiceProviders serviceProviders = modelMapper.map(registerRequestDto, ServiceProviders.class);
        HashSet<Category> categoryHashSet = new HashSet<>();
        categoryHashSet.addAll(categoryList);
        serviceProviders.setCategories(categoryHashSet);
        serviceProviders.setLocation(location1);
        serviceProviders.setPassword(this.passwordEncoder.encode(registerRequestDto.getPassword()));
        return serviceProvidersRepo.save(serviceProviders);
    }

    @Cacheable(cacheNames = CACHE_NAME,key = "#phone")
    public ServiceProviders findByPhone(String phone) {
        log.info("try to find the user by phone number ");
        Optional<ServiceProviders> optional = serviceProvidersRepo.findByPhone(phone);
        if (optional.isEmpty()) {
            log.info("cannot find user registered with this number");
            throw new PhoneNotExist("Phone number not registered yet");
        }
        return optional.get();
    }

    @Cacheable(cacheNames = CACHE_NAME3,key = "#id")
    public ServiceProviders findById(Long id){
        ServiceProviders optional=serviceProvidersRepo.findById(id).get();
        return optional;
    }

    @Cacheable(cacheNames = CACHE_NAME2,key = "#id")
    public ProfileResponseDto getProfile(Long id) {
        ServiceProviders serviceProviders = serviceProvidersService.findById(id);
        log.info("map to the profile Response Dto");
        ProfileResponseDto profileResponseDto = modelMapper.map(serviceProviders, ProfileResponseDto.class);
        profileResponseDto.setCategories_name(serviceProviders.getCategories().stream()
                .map((x) -> {
                    return x.getName();
                }).collect(Collectors.toSet()));
        Location location=serviceProviders.getLocation();
        List<String> locations= Arrays.asList(location.getState(),location.getDistrict(),location.getBlock(),location.getVillage());
        log.info("mapping to the locations to the response dto");
        profileResponseDto.setLocation(locations);
        return profileResponseDto;
    }
}




