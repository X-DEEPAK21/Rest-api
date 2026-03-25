package com.service.services.Repository;

import com.service.services.Entity.ServiceProviders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceProvidersRepo extends JpaRepository<ServiceProviders,Long> {

    Optional<ServiceProviders> findByPhone(String phone);

    @Override
    Page<ServiceProviders> findAll(Pageable pageable);
//// Location methods
    Page<ServiceProviders> findByLocation_State(String state,Pageable pageable);

    Page<ServiceProviders> findByLocation_District(String district, Pageable pageable);

    Page<ServiceProviders> findByLocation_Block(String block, Pageable pageable);

    Page<ServiceProviders> findByLocation_Village(String village, Pageable pageable);
//// skill categories methods
    Page<ServiceProviders> findByCategories_Name(String category, Pageable pageable);

    Page<ServiceProviders> findByCategories_NameAndLocation_State(
            String category,
            String state,
            Pageable pageable);
    Page<ServiceProviders> findByCategories_NameAndLocation_StateAndLocation_District(
            String category,
            String state,
            String district,
            Pageable pageable
    );
    Page<ServiceProviders> findByCategories_NameAndLocation_StateAndLocation_DistrictAndLocation_Block(
            String category,
            String state,
            String district,
            String block,
            Pageable pageable);
    Page<ServiceProviders> findByCategories_NameAndLocation_StateAndLocation_DistrictAndLocation_BlockAndLocation_Village(
            String category,
            String state,
            String district,
            String block,
            String village,
            Pageable pageable);


}
