package com.service.services.RequestDtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;

@Builder
@Data
public class VerifyOtpRequest {
  String phone;
  String otp;
}
