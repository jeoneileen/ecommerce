package com.ecommerce.category.application.port.in;

import com.ecommerce.category.domain.Category;
import java.util.List;

public interface GetCategoryUseCase {
    Category getCategory(Long categoryId);
    List<Category> getAllCategories();
    List<Category> getChildCategories(Long parentId);
}