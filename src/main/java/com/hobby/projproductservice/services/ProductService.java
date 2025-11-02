package com.hobby.projproductservice.services;

import com.hobby.projproductservice.exceptions.ProductNotFoundException;
import com.hobby.projproductservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product p);

    Product updateProduct(Long id, Product p);

    boolean deleteProduct(Long id);
}
