package com.service.services.Controller;

import com.service.services.ResponseDtos.ProfileResponseDto;
import com.service.services.Service.UserService;
import com.service.services.SpringSecurity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")//// automatic add the prefix ROLE_
    public ResponseEntity<ProfileResponseDto> getUserProfile(){
     UserDetailsImpl userDetails =(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     Long userId=userDetails.getServiceProviders().getId();
     ProfileResponseDto profileResponseDto= userService.getProfile(userId);
     return ResponseEntity.status(HttpStatus.FOUND).body(profileResponseDto);
    }
    //// service providers update email and user category also

}
