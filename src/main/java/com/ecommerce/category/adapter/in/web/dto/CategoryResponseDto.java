package com.ecommerce.category.adapter.in.web.dto;

import com.ecommerce.category.domain.Category;

import java.time.LocalDateTime;

public class CategoryResponseDto {

    private final Long categoryId;
    private final String categoryName;
    private final Long parentId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private CategoryResponseDto(Long categoryId, String categoryName, Long parentId,
                                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 도메인 → ResponseDto 변환
    public static CategoryResponseDto fromDomain(Category category) {
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getParentId(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public Long getParentId() { return parentId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}