package com.service.services.Service;

import com.service.services.Entity.Category;
import com.service.services.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    private static final String CACHE_NAME = "category";

    @Cacheable(
            cacheNames = CACHE_NAME,
            key = "T(java.util.Arrays).toString(#ids.stream().sorted().toArray())"
            // ✅ sort before caching — same IDs always = same key
    )
    public List<Category> categoryListByIds(Set<Long> ids){
        List<Category> categories=categoryRepo.findAllByIdIn(ids);
        return categories;
    }
}
