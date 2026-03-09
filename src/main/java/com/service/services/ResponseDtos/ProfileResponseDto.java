package com.service.services.ResponseDtos;

import com.service.services.Entity.Gender;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ProfileResponseDto {
    String full_name;
    String phone;
    String email;
    Gender gender;
    String profile_url;

    //Work Experience
    Set<String> categories_name;
    Integer experience;
    String workDescription;
    List<String> Location;
}
