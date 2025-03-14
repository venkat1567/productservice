package com.example.productservice.controllers;


import com.example.productservice.dtos.products.CreateProductDto;
import com.example.productservice.dtos.products.GetAllProductsResponseDto;
import com.example.productservice.dtos.products.GetProductDto;
import com.example.productservice.dtos.products.PatchProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.example.productservice.dtos.ErrorResponseDto;
import com.example.productservice.dtos.products.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;




    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product = productService.createProduct(
                createProductRequestDto.toProduct()
        );

        return CreateProductResponseDto.fromProduct(
                product
        );


    }

    @GetMapping("")
    public GetAllProductsResponseDto getAllProducts() {
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto response = new GetAllProductsResponseDto();

        List<GetProductDto> getProductResponseDtos = new ArrayList<>();

        for (Product product: products) {
            getProductResponseDtos.add(GetProductDto.from(product));
        }

        response.setProducts(getProductResponseDtos);

        return response;
    }

    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long id) {
        return "Here is your product: " + id;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }

    @PatchMapping("/{id}")
    public PatchProductResponseDto updateProduct(
            @PathVariable("id") Long productId,
            @RequestBody CreateProductDto productDto
    ) {
        Product product = productService.partialUpdateProduct(
                productId,
                productDto.toProduct()
        );

        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));

        return response;
    }

    public void replaceProduct() {}

//    @RequestMapping(name = "NAMAN", value = "/products/")
//    public String tabgushijhd() {
//        return "Magic";
//    }

}