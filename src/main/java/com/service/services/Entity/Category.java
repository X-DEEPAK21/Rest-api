package com.service.services.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY)
    private List<ServiceProviders> serviceProvidersSet;

    public Category(String name) {
        this.name = name;
    }
}
