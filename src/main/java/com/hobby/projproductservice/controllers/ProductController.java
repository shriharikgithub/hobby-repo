package com.hobby.projproductservice.controllers;

import com.hobby.projproductservice.commons.AuthenticationCommons;
import com.hobby.projproductservice.dtos.fakestore.*;
import com.hobby.projproductservice.dtos.fakestore.ResponseStatus;
import com.hobby.projproductservice.exceptions.ProductNotFoundException;
import com.hobby.projproductservice.models.Product;
import com.hobby.projproductservice.services.MySQLProductService;
import com.hobby.projproductservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier(value = "fakeStoreProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping
    public ResponseEntity<GetAllProductsResponseDto> getAllProducts() {
        GetAllProductsResponseDto response = new GetAllProductsResponseDto();

//        ValidateTokenResponseDto validateTokenResponseDto = authenticationCommons.validateToken(token);
//        if (validateTokenResponseDto.getResponseStatus().equals(ResponseStatus.FAILURE)) {
//            response.setResponseStatus(ResponseStatus.FAILURE);
//            response.setMessage("This endpoint is forbidden to be used by this user.");
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
//        }
//        boolean isAdmin = validateTokenResponseDto.getRoles().stream().anyMatch(roleDto -> roleDto.getName().equalsIgnoreCase("admin"));
//
//        if (!isAdmin) {
//            response.setResponseStatus(ResponseStatus.FAILURE);
//            response.setMessage("Invalid token, You are not authorized to access this endpoint.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }

        List<Product> products = productService.getAllProducts();
        response.setMessage("Fetched all products successfully");
        response.setResponseStatus(ResponseStatus.SUCCESS);
        List<ProductDto> productDtos = new ArrayList<>();
        products.stream().forEach(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setPrice(p.getPrice());
            productDto.setCategoryId(p.getCategory().getId());
            productDto.setCategoryName(p.getCategory().getName());
            productDto.setTitle(p.getTitle());
            productDto.setImageUrl(p.getImageUrl());
            productDtos.add(productDto);
        });
        response.setProducts(productDtos);

        ResponseEntity<GetAllProductsResponseDto> responseEntity = new ResponseEntity<>(
                response,
            HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
