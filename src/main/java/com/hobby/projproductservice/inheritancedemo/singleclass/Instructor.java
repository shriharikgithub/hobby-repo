package com.hobby.projproductservice.inheritancedemo.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "st_instructor")
@DiscriminatorValue(value = "instructor")
public class Instructor extends User {
    private String favoriteStudent;
}
