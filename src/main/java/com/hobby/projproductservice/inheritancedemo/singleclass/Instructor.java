package com.hobby.projproductservice.inheritancedemo.singleclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
    private String favoriteStudent;
}
