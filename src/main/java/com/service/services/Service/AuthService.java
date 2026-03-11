package com.service.services.Service;

import com.service.services.Entity.ServiceProviders;
import com.service.services.Repository.ServiceProvidersRepo;
import com.service.services.RequestDtos.LoginRequestDto;
import com.service.services.RequestDtos.LoginResponseDto;
import com.service.services.SpringSecurity.Token;
import com.service.services.SpringSecurity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    Token jwttoken;
    @Autowired
    ServiceProvidersRepo serviceProvidersRepo;


    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginRequestDto.getPhone(),loginRequestDto.getPassword());
        Authentication authentication=authenticationManager.authenticate(token);
        ServiceProviders serviceProviders=((UserDetailsImpl)authentication.getPrincipal()).getServiceProviders();

        String accessToken=jwttoken.generateAccessToken(serviceProviders);
        String refreshToken=jwttoken.generateRefreshToken(serviceProviders);
        return LoginResponseDto.builder().servicerId(serviceProviders.getId())
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId = jwttoken.getUserIdFromToken(refreshToken);
        ServiceProviders serviceProviders=serviceProvidersRepo.findById(userId).get();
        String accessToken = jwttoken.generateAccessToken(serviceProviders);

         return LoginResponseDto.builder().servicerId(serviceProviders.getId())
                 .accessToken(accessToken)
                 .refreshToken(refreshToken).build();
    }

}
