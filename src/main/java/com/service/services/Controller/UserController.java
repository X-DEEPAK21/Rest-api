package com.service.services.Controller;

import com.service.services.ResponseDtos.ProfileResponseDto;
import com.service.services.Service.UserService;
import com.service.services.SpringSecurity.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")//// automatic add the prefix ROLE_
    public ResponseEntity<ProfileResponseDto> getUserProfile(){
        log.info("request reached at the controller ");
     UserDetailsImpl userDetails =(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     Long userId=userDetails.getServiceProviders().getId();
     ProfileResponseDto profileResponseDto= userService.getProfile(userId);
     log.info("response successfully back from the service return it");
     return ResponseEntity.status(HttpStatus.FOUND).body(profileResponseDto);
    }
    //// service providers able to update email and user category also need to create
    ///
    @PatchMapping("/user/update-profile")
    public ResponseEntity<ProfileResponseDto> updateUserProfile(){
    log.info("request reached at the controller lets forward it to the service ");


    }

}
