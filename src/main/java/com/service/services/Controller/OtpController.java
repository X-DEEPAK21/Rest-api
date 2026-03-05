package com.service.services.Controller;

import com.service.services.RequestDtos.RequestOtp;
import com.service.services.RequestDtos.VerifyOtpRequest;
import com.service.services.ResponseDtos.OtpResponse;
import com.service.services.Service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {

    SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<OtpResponse> sendOtp(@RequestBody RequestOtp request) {
        return ResponseEntity.ok(smsService.sendOtp(request.getPhone()));
    }
    @PostMapping("/verify")
    public ResponseEntity<OtpResponse> verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
        return ResponseEntity.ok(smsService.verifyOtp(verifyOtpRequest.getPhone(),verifyOtpRequest.getOtp()));
    }

}
