package com.service.services.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
@Builder
@Entity
@Table(name = "locations",
        indexes = {
                @Index(name = "idx_state", columnList = "State"),
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
   private Long id;
    @Column(length =20)
   private String State;
    @Column(length =20)
   private String district;
    @Column(length =20)
   private String block;
    @Column(length =20)
   private String village;

   @OneToMany(mappedBy = "location",fetch = FetchType.EAGER)
   Set<ServiceProviders> serviceProviders;

}
