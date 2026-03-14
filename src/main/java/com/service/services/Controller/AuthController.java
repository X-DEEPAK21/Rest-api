package com.service.services.Controller;


import com.service.services.Entity.ServiceProviders;
import com.service.services.RequestDtos.LoginRequestDto;
import com.service.services.ResponseDtos.LoginResponseDto;
import com.service.services.RequestDtos.RegisterRequestDto;
import com.service.services.ResponseDtos.RegistrationResponseDto;
import com.service.services.Service.AuthService;
import com.service.services.Service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> createUser(@RequestBody RegisterRequestDto registerRequestDto){
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request,
                                                      HttpServletResponse httpResponse){
       LoginResponseDto loginResponseDto= authService.login(loginRequestDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        httpResponse.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies()).//// get Cookies return the arrays of element
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie->{
                    return cookie.getValue();
                })
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));
        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);
    }



}
