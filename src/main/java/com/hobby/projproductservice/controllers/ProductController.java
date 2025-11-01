package com.hobby.projproductservice.controllers;

import com.hobby.projproductservice.dtos.fakestore.FakeStoreProducDto;
import com.hobby.projproductservice.dtos.fakestore.ProductNotFoundExceptionDto;
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

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier(value = "mySQLProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(
            productService.getAllProducts(),
            HttpStatus.ACCEPTED
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
        return new Product();
    }

    @PutMapping("{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }
}
