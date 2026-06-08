package com.ecommerce.category.application.port.out;

import com.ecommerce.category.domain.Category;

public interface SaveCategoryPort {
    Long saveCategory(Category category);
}