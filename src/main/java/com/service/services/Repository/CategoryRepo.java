package com.service.services.Repository;

import com.service.services.Entity.Category;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {


    public List<Category> findAllByIdIn(Set<Long> ids);

}
