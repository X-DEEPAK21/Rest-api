package com.service.services.ResponseDtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpResponse {
    Boolean success;
    String message;
}
