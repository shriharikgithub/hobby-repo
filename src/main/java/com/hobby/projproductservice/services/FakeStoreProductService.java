package com.hobby.projproductservice.services;
import com.hobby.projproductservice.exceptions.ProductNotFoundException;
import com.hobby.projproductservice.models.Category;
import com.hobby.projproductservice.models.Product;
import com.hobby.projproductservice.dtos.fakestore.FakeStoreProducDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        FakeStoreProducDto fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProducDto.class);
        if (fakeStoreProduct == null) {
            throw new ProductNotFoundException();
        }
        Product product = new Product();
        product.setId(fakeStoreProduct.getId());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setDescription(fakeStoreProduct.getDescription());
        Category c = new Category();
        c.setId(1L);
        c.setName(fakeStoreProduct.getCategory());
        product.setCategory(c);
        product.setImageUrl(fakeStoreProduct.getImage());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<FakeStoreProducDto[]> response = restTemplate.exchange("https://fakestoreapi.com/products", HttpMethod.GET, entity, FakeStoreProducDto[].class);
        FakeStoreProducDto[] fakeStoreProducts = response.getBody();
        List<Product> products = Arrays.stream(fakeStoreProducts)
                .map(fakeStoreProduct -> {
                    Product product = new Product();
                    product.setTitle(fakeStoreProduct.getTitle());
                    product.setDescription(fakeStoreProduct.getDescription());
                    product.setId(fakeStoreProduct.getId());
                    Category c = new Category();
                    c.setId(1L);
                    c.setName(fakeStoreProduct.getCategory());
                    product.setCategory(c);
                    product.setImageUrl(fakeStoreProduct.getImage());
                    return product;
                }).toList();
        return products;
    }

    @Override
    public Product createProduct(Product p) {
        FakeStoreProducDto fakeStoreProducDto = new FakeStoreProducDto();
        fakeStoreProducDto.setImage(p.getImageUrl());
        fakeStoreProducDto.setPrice(p.getPrice());
        fakeStoreProducDto.setDescription(p.getDescription());
        fakeStoreProducDto.setCategory(p.getCategory().getName());
        fakeStoreProducDto.setTitle(p.getTitle());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity with body + headers
        HttpEntity<FakeStoreProducDto> requestEntity = new HttpEntity<>(fakeStoreProducDto, headers);

        ResponseEntity<FakeStoreProducDto> response = restTemplate.exchange("https://fakestoreapi.com/products", HttpMethod.POST, requestEntity, FakeStoreProducDto.class);
        FakeStoreProducDto responseFakeStoreProducDto = response.getBody();
        Product responseProduct = new Product();
        Category category = new Category();
        category.setName(responseFakeStoreProducDto.getCategory());
        responseProduct.setCategory(category);
        responseProduct.setPrice(responseFakeStoreProducDto.getPrice());
        responseProduct.setTitle(responseFakeStoreProducDto.getTitle());
        responseProduct.setDescription(responseFakeStoreProducDto.getDescription());
        responseProduct.setImageUrl(responseFakeStoreProducDto.getImage());
        responseProduct.setId(responseFakeStoreProducDto.getId());
        return responseProduct;
    }

    @Override
    public Product updateProduct(Long id, Product p) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
