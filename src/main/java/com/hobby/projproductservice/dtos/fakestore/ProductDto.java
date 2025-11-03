package com.hobby.projproductservice.dtos.fakestore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private Long categoryId;
    private String categoryName;
    private String imageUrl;

}
