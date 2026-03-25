package com.service.services.Service;

import com.service.services.Entity.Location;
import com.service.services.Entity.ServiceProviders;
import com.service.services.Mapping.UniversalMapping;
import com.service.services.Repository.ServiceProvidersRepo;
import com.service.services.ServiceDtos.PageResponse;
import com.service.services.ServiceDtos.ServiceProvidersDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Service
public class AllService {
    @Autowired
    ServiceProvidersRepo serviceProvidersRepo;
    @Autowired
    ModelMapper modelMapper;  //always need no args Constructor
    @Autowired
    UniversalMapping universalMapping;
    private static final String CACHE_NAME="category";
    private static final String CACHE_NAME1="by_state";
    private static final String CACHE_NAME2="by_district";
    private static final String CACHE_NAME3="by_block";
    private static final String CACHE_NAME4="by_village";

    ////findBy Categories name only
    @Cacheable(cacheNames = CACHE_NAME,key = "{#category,#page,#size}",unless = "#result == null")
    @Transactional
    public PageResponse findByCategoriesName(String category, int page, int size){
         log.info("try to find serviceProviders by category name {}",category);
         Pageable pageable=PageRequest.of(page,size);
       Page<ServiceProviders> serviceProviders= serviceProvidersRepo.findByCategories_Name(category,pageable);
       log.info("find successful Time delegates to other class for mapping");
       PageResponse pageResponse= universalMapping.getMapped(serviceProviders);
       log.info("map successful lets return controller ");
       return pageResponse;
    }
    /// find by only state
    @Cacheable(cacheNames =CACHE_NAME1,key="{#state,#page,#size}",unless = "#result == null")
    @Transactional
     public PageResponse findByStateName(String state,int page,int size){
        log.info("try to find the service providers according to the state :{}",state);
        Pageable pageable=PageRequest.of(page,size);
        Page<ServiceProviders> serviceProviders=serviceProvidersRepo.findByLocation_State(state,pageable);
        log.info("find successful now it delegates to mapping ");
        PageResponse pageResponse=universalMapping.getMapped(serviceProviders);
        log.info("map successful lets return the controller ");
        return pageResponse;
     }

     @Cacheable(cacheNames =CACHE_NAME2,key="{#district,#page,#size}",unless = "#result == null")
     @Transactional
     public PageResponse findByDistrictName(String district,int page,int size){
         log.info("try to find the serviceProviders according to the the district :{}",district);
         Pageable pageable=PageRequest.of(size,page);
        Page<ServiceProviders> serviceProviders= serviceProvidersRepo.findByLocation_District(district,pageable);
        log.info("find successful lets delegates for mapping {}",district);
        PageResponse pageResponse=universalMapping.getMapped(serviceProviders);
        log.info("map successful lets return the Controller ");
        return pageResponse;
     }

    @Cacheable(cacheNames =CACHE_NAME3,key="{#block,#page,#size}",unless = "#result == null")
    @Transactional
     public PageResponse findByBlockName(String block,int page,int size){
         log.info("try to fetch the serviceProviders based on the Block {}",block);
         Pageable pageable=PageRequest.of(size,page);
         Page<ServiceProviders> serviceProviders=serviceProvidersRepo.findByLocation_Block(block,pageable);
         log.info("find successful lets delegates for mapping ");
         PageResponse pageResponse=universalMapping.getMapped(serviceProviders);
         log.info("map successful lets return the Controller ");
         return pageResponse;
     }
    @Cacheable(cacheNames =CACHE_NAME3,key="{#village,#page,#size}",unless = "#result == null")
    @Transactional
     public PageResponse findByVillageName(String village,int page, int size){
         log.info("try to fetch the serviceProvides based on the basis of village {}",village);
         Pageable pageable=PageRequest.of(size,page);
         Page<ServiceProviders> serviceProviders=serviceProvidersRepo.findByLocation_Village(village,pageable);
         log.info("find successful delegates the request for mapping");
         PageResponse pageResponse=universalMapping.getMapped(serviceProviders);
         log.info("map successful lets return the response to the controller");
         return pageResponse;
     }








}
