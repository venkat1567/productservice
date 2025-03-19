package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

}
