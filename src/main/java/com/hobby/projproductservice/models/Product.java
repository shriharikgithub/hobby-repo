package com.hobby.projproductservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "productz")
public class Product extends BaseModel {
    private String title;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    private String description;
    private String imageUrl;
}
