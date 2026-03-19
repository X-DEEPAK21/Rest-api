package com.service.services.Service;

import com.service.services.Entity.ServiceProviders;
import com.service.services.Exception.PhoneAlreadyExist;
import com.service.services.Repository.ServiceProvidersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ServiceProvidersService {
    @Autowired
    ServiceProvidersRepo serviceProvidersRepo;
    private static final String CACHE_NAME="userbyid";

    @Cacheable(cacheNames = CACHE_NAME,key = "#id")
    public ServiceProviders findById(Long id){
        ServiceProviders optional=serviceProvidersRepo.findById(id).get();
        return optional;
    }

    public ServiceProviders serviceFindByPhone(String phone){
       return serviceProvidersRepo.findByPhone(phone).orElseThrow(()->{
           log.error("");
            return new PhoneAlreadyExist("phone number already registered ");
       });
    }
}
