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
    }

    @Override
    public Product createProduct(Product p) {
        Optional<Category> optionalCategory =
                p.getCategory().getId() != null ? categoryRepository.findById(p.getCategory().getId()) :
                Optional.empty();
        Category category = null;
        if (optionalCategory.isEmpty()) {
            category = categoryRepository.save(p.getCategory());
        } else {
            category = optionalCategory.get();
        }
        p.setCategory(category);
        return productRepository.save(p);
    }
}
