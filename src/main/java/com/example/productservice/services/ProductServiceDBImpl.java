package com.example.productservice.services;


import org.springframework.stereotype.Service;
import java.util.List;
import com.example.productservice.models.Product;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService {

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {
        return null;
    }
}