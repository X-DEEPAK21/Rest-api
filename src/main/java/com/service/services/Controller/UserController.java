package com.service.services.Controller;

import com.service.services.Entity.ServiceProviders;
import com.service.services.RequestDtos.LoginRequestDto;
import com.service.services.RequestDtos.RegisterRequestDto;
import com.service.services.ResponseDtos.RegistrationResponseDto;
import com.service.services.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user/provider")
public class UserController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequestDto registerRequestDto){
      log.info("user forward to service for registration");
      ServiceProviders serviceProviders= userService.createUser(registerRequestDto);
        RegistrationResponseDto registerResponseDto= modelMapper.map(serviceProviders, RegistrationResponseDto.class);
        registerResponseDto.setCategories_name(serviceProviders.getCategories().stream()
                .map((x)->{
                    return x.getName();
                }).collect(Collectors.toSet()));

      log.info("return the service provider Details from db");
      return ResponseEntity.status(HttpStatus.CREATED).body(registerResponseDto);
    }

    //security Details addition after the
    public ResponseEntity<?>loginUser(@RequestBody LoginRequestDto loginRequestDto){
       return ResponseEntity.ok("Deepak");
    }


   /* @GetMapping("/profile")
    public ResponseEntity<?>getProfile(){

    }*/



}
