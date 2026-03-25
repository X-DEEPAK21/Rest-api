package com.service.services.Controller;


import com.service.services.Entity.ServiceProviders;
import com.service.services.Service.AllService;
import com.service.services.ServiceDtos.PageResponse;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/single-get")
public class ServiceController {
    @Autowired
    AllService allService;

    @GetMapping("/get-by-category")
     public ResponseEntity<?> getServiceProvidersByCategoryName(@RequestParam String category,@RequestParam int page,@RequestParam int size){
        PageResponse pageResponse= allService.findByCategoriesName(category,page,size);
         return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
     }

     public ResponseEntity<?>
}
