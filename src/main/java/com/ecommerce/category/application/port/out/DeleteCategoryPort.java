package com.ecommerce.category.application.port.out;

import com.ecommerce.category.domain.Category;

public interface DeleteCategoryPort {
    void deleteCategory(Category category);
}