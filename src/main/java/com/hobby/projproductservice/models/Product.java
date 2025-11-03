package com.hobby.projproductservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private Double price;

//    @ManyToOne(cascade = CascadeType.ALL)
    // CascadeType.ALL IF something happens to product, do same thing to all categories too
    // If product is deleted, category is also deleted.

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    // Only save is cascaded if category doesn't exist.
    private Category category;

    private String description;
    private String imageUrl;

    private int numberOfSales;

}
