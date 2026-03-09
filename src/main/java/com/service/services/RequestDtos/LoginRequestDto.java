package com.service.services.RequestDtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    String phone;
    String password;
}
