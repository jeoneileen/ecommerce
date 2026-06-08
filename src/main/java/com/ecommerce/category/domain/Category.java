package com.ecommerce.category.domain;

import java.time.LocalDateTime;

public class Category {

    private final Long categoryId;
    private String categoryName;
    private final Long parentId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Category(Long categoryId, String categoryName, Long parentId,
                    LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // 신규 카테고리 생성
    public static Category create(String categoryName, Long parentId) {
        LocalDateTime now = LocalDateTime.now();
        return new Category(null, categoryName, parentId, now, now, null);
    }

    // DB 조회 결과 복원
    public static Category restore(Long categoryId, String categoryName, Long parentId,
                                   LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        return new Category(categoryId, categoryName, parentId, createdAt, updatedAt, deletedAt);
    }

    // 카테고리명 수정
    public void updateName(String categoryName) {
        this.categoryName = categoryName;
        this.updatedAt = LocalDateTime.now();
    }

    // 소프트 삭제
    public void delete() {
        this.deletedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public Long getParentId() { return parentId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public LocalDateTime getDeletedAt() { return deletedAt; }
}