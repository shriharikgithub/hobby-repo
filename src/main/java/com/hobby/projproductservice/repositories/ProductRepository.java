package com.hobby.projproductservice.repositories;

import com.hobby.projproductservice.models.Category;
import com.hobby.projproductservice.models.Product;
import com.hobby.projproductservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();

    // select * from products where title like "%word%"
    List<Product> findByTitleContaining(String word);

    Long deleteByTitle(String title);

    List<Product> findByPriceLessThanEqual(double price);

    List<Product> findByPriceBetween(double startRange, double endRange);

    List<Product> findByCategoryOrderByTitle(Category category);

    Optional<Product> findByIdAndCategory(Long id, Category category);

    List<Product> findByCategory_Id(Long categoryId);

    Product save(Product entity);

    void deleteById(Long id);

    @Query("select p.id as id, p.title as title from Product p where p.id = 2")
    List<ProductWithIdAndTitle> customQuery();


    @Query(value = "select p.id as id, p.title as title from product p where p.id = 2", nativeQuery = true)
    List<ProductWithIdAndTitle> customSQLQuery();

    @Query(value = "select * from product p where p.id = 2", nativeQuery = true)
    List<Product> customSQLQuerySelectAllAttributes();

    @Query(value = "select * from product p", nativeQuery = true)
    List<Product> customSQLQuerySelectAll();


    @Query(value = "select * from product p where p.id = id", nativeQuery = true)
    List<Product> customSQLQueryWithId(@Param("id") Long id);
}
