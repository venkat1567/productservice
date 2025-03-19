package com.example.productservice.services;


import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.models.Subcategory;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.repositories.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SubcategoryRepository subcategoryRepository;
    public ProductServiceDBImpl(ProductRepository productRepository,
                                CategoryRepository categoryRepository,SubcategoryRepository subcategoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Category toBePutInProduct = getCategoryToBeInProduct(product);

        product.setCategory(toBePutInProduct);


        Product savedProduct = productRepository.save(product);
        System.out.println("hahahhahaha");

        return savedProduct;
    }


    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product partialUpdateProduct(Long productId,
                                        Product product) throws ProductNotFoundException {

        Optional<Product> productToUpdateOptional = productRepository.findById(productId);

        if (productToUpdateOptional.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: " + productId + "");
        }

        Product productToUpdate = productToUpdateOptional.get();

        if (product.getDescription() != null) {
            productToUpdate.setDescription(product.getDescription());
        }

        if (product.getPrice() != null) {
            productToUpdate.setPrice(product.getPrice());
        }

        if (product.getTitle() != null) {
            productToUpdate.setTitle(product.getTitle());
        }

        if (product.getCategory() != null) {
            Category toBePutInProduct = getCategoryToBeInProduct(product);

            productToUpdate.setCategory(toBePutInProduct);
        }

        return productRepository.save(productToUpdate);
    }

    private Category getCategoryToBeInProduct(Product product) {
        String categoryName = product.getCategory().getName();

        Optional<Category> category =
                categoryRepository.findByName(categoryName);
        Category toBePutInProduct = null;

        if (category.isEmpty()) {
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);
           // Subcategory subcategory = new Subcategory();
           // subcategory.setSurname("Portable Speakers");
           // subcategoryRepository.save(subcategory);
            //toSaveCategory.setSubcategories(subcategory);
            toBePutInProduct = toSaveCategory;
        } else {
            toBePutInProduct = category.get();
        }
        return toBePutInProduct;
    }
}
