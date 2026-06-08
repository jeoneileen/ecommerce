package com.ecommerce.category.adapter.out.persistence;

import com.ecommerce.category.application.port.out.*;
import com.ecommerce.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements
        SaveCategoryPort,
        LoadCategoryPort,
        DeleteCategoryPort {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Long saveCategory(Category category) {
        CategoryJpaEntity entity = categoryJpaRepository.save(
                CategoryJpaEntity.fromDomain(category)
        );
        return entity.getCategoryId();
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryJpaRepository.findById(categoryId)
                .map(CategoryJpaEntity::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAllByDeletedAtIsNull()
                .stream()
                .map(CategoryJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return categoryJpaRepository.findByParentIdAndDeletedAtIsNull(parentId)
                .stream()
                .map(CategoryJpaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Category category) {
        // 소프트 삭제 - 도메인에서 deletedAt 세팅 후 저장
        categoryJpaRepository.save(CategoryJpaEntity.fromDomain(category));
    }
}