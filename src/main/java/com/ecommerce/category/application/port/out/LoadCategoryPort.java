package com.ecommerce.category.application.port.out;

import com.ecommerce.category.domain.Category;
import java.util.List;
import java.util.Optional;

public interface LoadCategoryPort {
    Optional<Category> findById(Long categoryId);
    List<Category> findAll();
    List<Category> findByParentId(Long parentId);
}