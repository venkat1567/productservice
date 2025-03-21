package com.example.productservice.services;




import com.example.productservice.dtos.fakestore.FakeStoreCreateProductRequestDto;
import com.example.productservice.dtos.fakestore.FakeStoreGetProductResponseDto;
import com.example.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
//@Primary
public class ProductServiceFakestoreImpl implements ProductService {

    private RestTemplate restTemplate;

    public ProductServiceFakestoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getCategory().getName());
        request.setTitle(product.getTitle());
        request.setImage(product.getImageUrl());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());

        FakeStoreGetProductResponseDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreGetProductResponseDto.class
        );


        // List<FakeStoreProductDto>

//         List<FakeStoreCreateProductResponseDto> response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                List<FakeStoreCreateProductResponseDto>.class
//        );

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        throw new RuntimeException();

//        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
//            "https://fakestoreapi.com/products",
//            FakeStoreGetProductResponseDto[].class
//        );
//
//        List<FakeStoreGetProductResponseDto> responseDtoList =
//                Stream.of(response).toList();
//
//        List<Product> products = new ArrayList<>();
//
//        for (FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto: responseDtoList) {
//            products.add(fakeStoreGetProductResponseDto.toProduct());
//        }
//
//        return products;
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {
//        FakeStoreGetProductResponseDto productResponseDto = restTemplate.exchange(
//                "https://fakestoreapi.com/products/" + productId,
//                HttpMethod.PATCH,
//                FakeStoreCreateProductRequestDto.fromProduct(product),
//                FakeStoreGetProductResponseDto.class
//        );

        HttpEntity<FakeStoreCreateProductRequestDto> requestEntity = new HttpEntity<>(FakeStoreCreateProductRequestDto.fromProduct(product));
        ResponseEntity<FakeStoreGetProductResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.PATCH,
                requestEntity,
                FakeStoreGetProductResponseDto.class
        );


        return responseEntity.getBody().toProduct();
    }

}