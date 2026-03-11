package com.service.services.SpringSecurity;

import com.service.services.Entity.ServiceProviders;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails {

    private ServiceProviders serviceProviders;

    public ServiceProviders getServiceProviders() {
        return serviceProviders;
    }

    public UserDetailsImpl(ServiceProviders serviceProviders){
       this.serviceProviders=serviceProviders;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(serviceProviders.getRole().name()));//// Authorities will be different
    }

    @Override
    public @Nullable String getPassword() {
        return this.serviceProviders.getPhone();
    }

    @Override
    public String getUsername() {
        return this.serviceProviders.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired(); ////account expiration handle
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.serviceProviders.isActive();  //// throw Locked Exception
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired(); ////when pass expire
    }

    @Override
    public boolean isEnabled() {
        return this.serviceProviders.isVerified();//// user Verification
    }
}
