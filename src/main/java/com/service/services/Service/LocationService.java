package com.service.services.Service;

import com.service.services.Entity.Location;
import com.service.services.Repository.LocationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
public class LocationService {
    @Autowired
    LocationRepo locationRepo;
    private static  final String CACHE_NAME="location";


    @Cacheable(cacheNames = CACHE_NAME,key ="{#state,#district,#block,#village}" )
    @Transactional
    public Location findLocation(String state, String district,String block,String village){
        log.info("find the location according the user location and hitting db");
        Optional<Location> optional = locationRepo.findByStateAndDistrictAndBlockAndVillage(state,district,block,village);
        if(optional.isEmpty()){
            log.info("location not found save the location inside the db  ");
            return locationRepo.save(Location.builder().state(state).district(district)
                    .block(block)
                    .village(village).build());
        }
        return optional.get();
    }


}
