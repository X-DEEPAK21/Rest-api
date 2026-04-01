package com.service.services.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Table(name = "categories",
        indexes={
            @Index(name ="idx_name",columnList ="name")
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;
    String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY)
    private List<ServiceProviders> serviceProvidersList;

    public Category(String name) {
        this.name = name;
    }
}
