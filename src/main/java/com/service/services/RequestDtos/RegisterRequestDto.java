package com.service.services.RequestDtos;

import com.service.services.Entity.Category;
import com.service.services.Entity.Gender;
import com.service.services.Entity.Location;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name cannot be blank")
    String full_name;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
    String phone;
    String email;
    Gender gender;
    String profile_url;

    //Work Experience
    Set<Long> categories_ids;
    Integer experience;
    String workDescription;
    //Location
    Location location;
    //Verification
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6)
    private String password;

}
