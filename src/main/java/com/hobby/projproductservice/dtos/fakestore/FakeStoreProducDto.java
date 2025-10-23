package com.hobby.projproductservice.dtos.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProduct {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
