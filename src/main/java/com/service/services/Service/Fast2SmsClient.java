package com.service.services.Service;

import com.service.services.RequestDtos.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "fast2sms", url = "${sms.fast2sms.base-url}")
public interface Fast2SmsClient {

    @PostMapping("/bulkV2")
    String sendSms(
            @RequestHeader("authorization") String apiKey,
            @RequestHeader("accept")        String accept,
            @RequestHeader("content-type")  String contentType,
            @RequestBody SmsRequest smsRequest
    );


}
