package com.hobby.projproductservice.dtos.fakestore;

import com.hobby.projproductservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductsResponseDto {
    private ResponseStatus responseStatus;
    private String message;
    private List<ProductDto> products;
}
