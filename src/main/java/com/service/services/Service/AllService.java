package com.service.services.Service;

import com.service.services.Entity.ServiceProviders;
import com.service.services.Repository.ServiceProvidersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AllService {
    @Autowired
    ServiceProvidersRepo serviceProvidersRepo;

     public Page<ServiceProviders>  checkRepositoryMethods(){
         int page=0;
         int size=10;
         Pageable pageable = PageRequest.of(page, size);
       Page<ServiceProviders> serviceProviders=
            serviceProvidersRepo.findByCategories_NameAndLocation_State("Electrician","Odisha",pageable);
         return serviceProviders;
     }

}
