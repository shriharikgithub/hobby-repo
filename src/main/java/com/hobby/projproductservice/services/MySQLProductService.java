package com.hobby.projproductservice.services;

import com.hobby.projproductservice.exceptions.ProductNotFoundException;
import com.hobby.projproductservice.models.Category;
import com.hobby.projproductservice.models.Product;
import com.hobby.projproductservice.repositories.CategoryRepository;
import com.hobby.projproductservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MySQLProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public MySQLProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
//        return productRepository.customQuery(0.122, "test");
    }

    @Override
    public Product createProduct(Product p) {
//        Category category = p.getCategory();
//        if (category.getId() == null) {
//            Category savedCategory = categoryRepository.save(category);
//            p.setCategory(savedCategory);
//        }
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(Long id, Product p) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException();
        }
        Product product = optionalProduct.get();
        if (p.getTitle() != null) {
            product.setTitle(p.getTitle());
        }
        if (p.getDescription() != null) {
            product.setDescription(p.getDescription());
        }
        if (p.getPrice() != null) {
            product.setPrice(p.getPrice());
        }

        if (p.getImageUrl() != null) {
            product.setImageUrl(p.getImageUrl());
        }

        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
