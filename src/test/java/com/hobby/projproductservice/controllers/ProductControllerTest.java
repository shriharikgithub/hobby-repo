package com.hobby.projproductservice.controllers;

import com.hobby.projproductservice.exceptions.ProductNotFoundException;
import com.hobby.projproductservice.models.Product;
import com.hobby.projproductservice.repositories.ProductRepository;
import com.hobby.projproductservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoSpyBean(name = "mySQLProductService")
    private ProductService productService;

    @MockitoBean
    private ProductRepository productRepository;

    @Test
    void testProductsSameAsService() {

        List<Product> productList = new ArrayList<>();
        Product p1 = new Product();
        p1.setTitle("iphone 15");
        productList.add(p1);

        Product p2 = new Product();
        p2.setTitle("iphone 15 pro max");
        productList.add(p2);

        Product p3 = new Product();
        p3.setTitle("iphone 15 pro");
        productList.add(p3);

        // Arrange
        when(
                productService.getAllProducts()
        ).thenReturn(
                productList
        );
        // Act
//        ResponseEntity<?> responseEntityProductList = productController.getAllProducts("");
//        List<Product> products = responseEntityProductList.getBody();

        // Assert
//        assertEquals(productList.size(), products.size());
//        for (int i = 0; i < productList.size(); i++) {
//            assertEquals(productList.get(i).getTitle(), products.get(i).getTitle());
//        }
//        assertEquals(productList, products);
    }

    @Test
    void testNonExistingProductThrowsException() throws ProductNotFoundException {

        // arrange
        when(
                productRepository.findById(100L)
        ).thenReturn(
                Optional.empty()
        );

        // act
        assertThrows(
                ProductNotFoundException.class,
                () -> productController.getSingleProduct(100L)
        );
    }

}