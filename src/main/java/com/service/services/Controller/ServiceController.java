package com.service.services.Controller;


import com.service.services.Entity.ServiceProviders;
import com.service.services.Service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class ServiceController {
    @Autowired
    AllService allService;

    @GetMapping("/al")
    public Page<ServiceProviders> getServicers(){
    return allService.checkRepositoryMethods();
    }


}
