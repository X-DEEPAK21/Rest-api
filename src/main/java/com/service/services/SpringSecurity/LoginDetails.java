package com.service.services.SpringSecurity;

import com.service.services.Entity.ServiceProviders;
import com.service.services.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class LoginDetails implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       ServiceProviders serviceProviders= userService.findByPhone(username);
        if(serviceProviders.isActive()==false){
            throw new LockedException("User is Blocked");
        }
      return new UserDetailsImpl(serviceProviders);
    }
}
