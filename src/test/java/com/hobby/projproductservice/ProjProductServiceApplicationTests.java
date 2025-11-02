package com.hobby.projproductservice;

import com.hobby.projproductservice.models.Product;
import com.hobby.projproductservice.repositories.ProductRepository;
import com.hobby.projproductservice.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ProjProductServiceApplicationTests {

    private ProductRepository productRepository;

    @Autowired
    public ProjProductServiceApplicationTests(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Test
//    void contextLoads() {
//    }

    @Test
    @Transactional
    @Commit
    void testQueries() {
        productRepository.findByTitleContaining("test-title");
        productRepository.deleteByTitle("test-title1");
    }

    @Test
    void testCustomQueries() {
        List<ProductWithIdAndTitle> products = productRepository.customQuery();
        for (ProductWithIdAndTitle productWithIdAndTitle : products) {
            System.out.println(productWithIdAndTitle.getTitle());
            System.out.println(productWithIdAndTitle.getId());
            System.out.println("----");
        }

        List<Product> products1 = productRepository.customSQLQuerySelectAll();
        System.out.println(products1);


        List<Product> products2 = productRepository.customSQLQueryWithId(2L);
        System.out.println(products2);
    }

}
