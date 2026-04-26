package com.service.services.PreDataLoad;

import com.service.services.Entity.Category;
import com.service.services.Repository.CategoryRepo;
import com.service.services.Repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepo.count() == 0) {
            List<Category> categories = Arrays.asList(
                            "Plumber", "Electrician", "Carpenter", "Painter", "AC Technician",
                            "Mechanic", "Mason", "Welder", "House Cleaner", "Pest Control",
                            "Gardener", "CCTV Technician", "Appliance Repair", "Locksmith",
                            "Interior Designer", "Roofer", "Tiling & Flooring Expert",
                            "Water Tank Cleaner", "Solar Panel Installer", "Smart Home Technician",
                            "Upholstery Cleaner", "Gutter Maintenance"
                    ).stream()
                    .map(Category::new)//new Categories("each_string")
                    .collect(Collectors.toList());

            // saveAll() is more efficient than multiple save() calls
            categoryRepo.saveAll(categories);
        }

    /*
    ('Plumber'),
('Electrician'),
('Carpenter'),
('Painter'),
('AC Technician'),
('Mechanic'),
('Mason'),
('Welder'),
('House Cleaner'),
('Pest Control'),
('Gardener'),
('CCTV Technician');

     */
    }
}