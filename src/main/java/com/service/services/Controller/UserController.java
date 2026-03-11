package com.service.services.Controller;

import com.service.services.SpringSecurity.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")//// automatic add the prefix ROLE_
    public ResponseEntity<?> getUserProfile(){
     UserDetailsImpl userDetails =(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     Long userId=userDetails.getServiceProviders().getId();
    }

}
