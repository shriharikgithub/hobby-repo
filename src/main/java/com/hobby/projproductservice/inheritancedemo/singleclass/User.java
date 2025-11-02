package com.hobby.projproductservice.inheritancedemo.singleclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "userType",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue(value = "User")
public class User {

    @Id
    private Long id;

    private String name;
    private String email;
}
