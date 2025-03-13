package com.example.productservice.services;


import org.springframework.stereotype.Service;
import com.example.productservice.models.Product;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService {

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}