package com.service.services.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
@Builder
@Entity
@Table(name = "locations",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"state","district","block","village"}),
        indexes = {
                @Index(name = "idx_state", columnList = "state"),
                @Index(name = "idx_district", columnList = "district"),
                @Index(name = "idx_block",columnList ="block" ),
                @Index(name = "idx_village", columnList = "village")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
   private Long id;
    @Column(length =20)
   private String state;
    @Column(length =20)
   private String district;
    @Column(length =20)
   private String block;
    @Column(length =20)
   private String village;

   @OneToMany(mappedBy = "location",fetch = FetchType.LAZY)
   @JsonIgnore
   Set<ServiceProviders> serviceProviders;

}

/*@Index(
    name = "idx_location_all",        this is fast composite index but allow only with the correct order
    columnList = "state,district,block,village"
)*/
