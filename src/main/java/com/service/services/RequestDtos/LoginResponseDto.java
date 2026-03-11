package com.service.services.RequestDtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

    Long servicerId;
    String refreshToken;
    String accessToken;

}
