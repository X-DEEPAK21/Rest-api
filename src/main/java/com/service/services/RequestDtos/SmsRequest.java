package com.service.services.RequestDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SmsRequest {
    @JsonProperty("route")
    private String route;       // "q"

    @JsonProperty("message")
    private String message;

    @JsonProperty("numbers")
    private String numbers;

    @JsonProperty("language")
    private String language;
}
