package com.ecommerce.category.application.service;

import com.ecommerce.category.application.port.in.*;
import com.ecommerce.category.application.port.out.*;
import com.ecommerce.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated  // 추가
@RequiredArgsConstructor
public class CategoryService implements
        CreateCategoryUseCase,
        GetCategoryUseCase,
        UpdateCategoryUseCase,
        DeleteCategoryUseCase {

    private final SaveCategoryPort saveCategoryPort;
    private final LoadCategoryPort loadCategoryPort;
    private final DeleteCategoryPort deleteCategoryPort;

    @Override
    @Transactional
    public Long createCategory(CreateCategoryCommand command) {
        // 부모 카테고리 존재 여부 검증
        if (command.parentId() != null) {
            loadCategoryPort.findById(command.parentId())
                    .filter(parent -> !parent.isDeleted())
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리를 찾을 수 없습니다."));
        }

        Category category = Category.create(command.categoryName(), command.parentId());
        return saveCategoryPort.saveCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategory(Long categoryId) {
        return loadCategoryPort.findById(categoryId)
                .filter(category -> !category.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return loadCategoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getChildCategories(Long parentId) {
        return loadCategoryPort.findByParentId(parentId);
    }

    @Override
    @Transactional
    public void updateCategory(Long categoryId, UpdateCategoryCommand command) {
        Category category = loadCategoryPort.findById(categoryId)
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        category.updateName(command.categoryName());
        saveCategoryPort.saveCategory(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = loadCategoryPort.findById(categoryId)
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        category.delete();
        deleteCategoryPort.deleteCategory(category);
    }
}