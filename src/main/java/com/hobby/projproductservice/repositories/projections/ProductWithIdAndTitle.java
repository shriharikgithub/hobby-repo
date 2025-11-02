package com.hobby.projproductservice.repositories.projections;

import lombok.Getter;
import lombok.Setter;

public interface ProductWithIdAndTitle {
    Long getId();
    String getTitle();
}
