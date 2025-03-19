package com.example.productservice.models;

import  jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    @Column(nullable = false, unique = true, name = "category_name")
    private String name;

    @Basic(fetch = FetchType.LAZY)
    private String description;

   // @OneToMany(fetch = FetchType.EAGER)
   // private List<Product> featuredProducts;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    private List<Product> allProducts;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Subcategory subcategories;

    private int countOfProducts;

}

