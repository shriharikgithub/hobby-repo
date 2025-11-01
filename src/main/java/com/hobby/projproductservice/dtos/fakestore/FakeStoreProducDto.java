package com.hobby.projproductservice.dtos.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProducDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
