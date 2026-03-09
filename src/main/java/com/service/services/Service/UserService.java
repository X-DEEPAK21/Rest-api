package com.service.services.Service;

import com.service.services.Entity.Category;
import com.service.services.Entity.Location;
import com.service.services.Entity.ServiceProviders;
import com.service.services.Exception.PhoneAlreadyExist;
import com.service.services.Exception.PhoneNotExist;
import com.service.services.Repository.CategoryRepo;
import com.service.services.Repository.LocationRepo;
import com.service.services.Repository.ServiceProvidersRepo;
import com.service.services.RequestDtos.LoginResponseDto;
import com.service.services.RequestDtos.RegisterRequestDto;
import com.service.services.ResponseDtos.ProfileResponseDto;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    CategoryRepo categoryRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    LocationRepo locationRepo;

    @Transactional
    public ServiceProviders createUser(RegisterRequestDto registerRequestDto) {
        log.info("find the service provider exist or not ");
        Optional<ServiceProviders> getProviders = serviceProvidersRepo.findByPhone(registerRequestDto.getPhone());
        if (getProviders.isPresent()) {
            throw new PhoneAlreadyExist("Phone number already Register");
        }
        log.info("save the location first");
        Location location = this.saveLocation(registerRequestDto.getLocation());

        log.info("find the category to get injected to the actual Entity");
        List<Category> categoryList = categoryRepo.findAllByIdIn(registerRequestDto.getCategories_ids());
        log.info("mapping to the actual Entity");
        ServiceProviders serviceProviders = modelMapper.map(registerRequestDto, ServiceProviders.class);
        HashSet<Category> categoryHashSet = new HashSet<>();
        categoryHashSet.addAll(categoryList);
        serviceProviders.setCategories(categoryHashSet);
        serviceProviders.setLocation(location);
        //password hashing Required
        return serviceProvidersRepo.save(serviceProviders);

    }

    public Location saveLocation(Location location) {
        log.info("find the location according the user location");
        Optional<Location> optional = locationRepo.findByStateAndDistrictAndBlockAndVillage(location.getState(), location.getDistrict()
                , location.getBlock(), location.getVillage());
        if (optional.isPresent()) {
            log.info("already present so return ");
            return optional.get();
        }
        log.info("creating new Location");
        return locationRepo.save(Location.builder().State(location.getState()).district(location.getDistrict())
                .block(location.getBlock()).village(location.getVillage()).build());
    }

    public ServiceProviders findByPhone(String phone) {
        log.info("try to find the user by phone number ");
        Optional<ServiceProviders> optional = serviceProvidersRepo.findByPhone(phone);
        if (optional.isEmpty()) {
            log.info("cannot find user registered with this number");
            throw new PhoneNotExist("Phone number not registered yet");
        }
        return optional.get();
    }
    // public LoginResponseDto loginUser(){
    //security call as well jwt verify all service implement here
    //  }

    public ProfileResponseDto getProfile(String phone) {
        ServiceProviders serviceProviders = this.findByPhone(phone);
        log.info("map to the profile Response Dto");
        ProfileResponseDto profileResponseDto = modelMapper.map(serviceProviders, ProfileResponseDto.class);
        profileResponseDto.setCategories_name(serviceProviders.getCategories().stream()
                .map((x) -> {
                    return x.getName();
                }).collect(Collectors.toSet()));
        Location location=serviceProviders.getLocation();
        List<String> locations= Arrays.asList(location.getState(),location.getDistrict(),location.getBlock(),location.getVillage());
        profileResponseDto.setLocation(locations);
        return profileResponseDto;
    }
}




