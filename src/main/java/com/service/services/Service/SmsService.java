package com.service.services.Service;

import com.service.services.RequestDtos.SmsRequest;
import com.service.services.ResponseDtos.OtpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SmsService {

    @Autowired
    Fast2SmsClient fast2SmsClient;

    @Value("${sms.fast2sms.api-key}")
    private String apiKey;

    private final Map<String, String> otpStore = new ConcurrentHashMap<>();

    public OtpResponse sendOtp(String phone) {

        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        SmsRequest smsRequest = SmsRequest.builder()
                .route("q")                             // ← Quick SMS
                .message("Your OTP is " + otp)
                .numbers(phone)
                .language("english")
                .build();

        fast2SmsClient.sendSms(apiKey, smsRequest);

        otpStore.put(phone, otp);

        return OtpResponse.builder()
                .success(true)
                .message("OTP sent to " + phone)
                .build();
    }

    public OtpResponse verifyOtp(String phone, String otp) {
        String savedOtp = otpStore.get(phone);

        if (savedOtp != null && savedOtp.equals(otp)) {
            otpStore.remove(phone);
            return OtpResponse.builder()
                    .success(true)
                    .message("OTP verified successfully")
                    .build();
        }

        return OtpResponse.builder()
                .success(false)
                .message("Invalid or expired OTP")
                .build();
    }


}
