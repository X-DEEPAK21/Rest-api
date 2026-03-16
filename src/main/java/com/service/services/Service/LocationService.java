package com.service.services.Service;

import com.service.services.Entity.Location;
import com.service.services.Repository.LocationRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
public class LocationService {
    @Autowired
    LocationRepo locationRepo;

    @Transactional
    public Location saveLocation( String state, String district,String block,String village) {
        log.info("find the location according the user location");
        Optional<Location> optional = locationRepo.findByStateAndDistrictAndBlockAndVillage(state,district,block,village);
        if (optional.isPresent()) {
            log.info("already present so return ");
            return optional.get();
        }
        log.info("creating new Location");
       return locationRepo.save(Location.builder().state(state).district(district)
                .block(block)
                .village(village).build());
    }

    //// methods only for user as well as admins
   /* public Location findLocation(){

    }*/
}
