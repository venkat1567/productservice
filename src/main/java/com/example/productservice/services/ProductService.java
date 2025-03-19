package com.example.productservice.services;


import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();



    Product partialUpdateProduct(Long productId, Product product) throws ProductNotFoundException;
}