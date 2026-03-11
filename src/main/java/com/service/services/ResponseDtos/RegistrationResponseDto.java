package com.service.services.ResponseDtos;

import com.service.services.Entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
@Data
public class RegistrationResponseDto {


    String full_name;
    String phone;
    String email;
    Gender gender;
    String profile_url;

    //Work Experience
    Set<String> categories_name;
    Integer experience;
    String workDescription;


}
