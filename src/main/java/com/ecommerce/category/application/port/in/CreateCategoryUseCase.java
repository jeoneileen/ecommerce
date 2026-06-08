package com.ecommerce.category.application.port.in;

public interface CreateCategoryUseCase {
    Long createCategory(CreateCategoryCommand command);
}