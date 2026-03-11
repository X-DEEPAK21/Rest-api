package com.service.services.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_providers")
public class ServiceProviders {
    //personal info
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
   private String full_name;
    @Column(unique = true,nullable = false,length = 10)
   private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String profile_url;

     //work description
    @ManyToMany
    private Set<Category> categories;

    private Integer experience;
    @Column(length = 300)
    private String workDescription;
     // Location
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    //verification
    @Column(nullable = false)
    @Size(min=5)
    private String password;

       // when admin took action
    @Column(name = "is_active")
    private boolean isActive = true;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "is_verified")
    private boolean isVerified=true;
    @Column(length = 255)
    private String rejectionReason;
    private LocalDateTime reviewedAt;

    @Column(updatable = false,name = "registered_at")
    private LocalDateTime registeredAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    public void onCreation(){
     this.registeredAt=LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate(){
        this.registeredAt=LocalDateTime.now();
    }





}


