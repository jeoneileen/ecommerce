package com.ecommerce.category.application.port.in;

public interface UpdateCategoryUseCase {
    void updateCategory(Long categoryId, UpdateCategoryCommand command);
}