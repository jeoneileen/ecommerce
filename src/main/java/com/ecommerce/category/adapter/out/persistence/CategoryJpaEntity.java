package com.ecommerce.category.adapter.out.persistence;

import com.ecommerce.category.domain.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카테고리ID")
    @Column(name = "category_id")
    private Long categoryId;

    @Comment("카테고리명")
    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @Comment("상위 카테고리ID, NULL이면 최상위 카테고리")
    @Column(name = "parent_id")
    private Long parentId;

    @Comment("생성일시")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Comment("수정일시")
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Comment("카테고리 삭제일시, NULL이면 사용중")
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    private CategoryJpaEntity(Long categoryId, String categoryName, Long parentId,
                              LocalDateTime createdAt, LocalDateTime updatedAt,
                              LocalDateTime deletedAt) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // 도메인 → JPA Entity 변환
    static CategoryJpaEntity fromDomain(Category category) {
        return new CategoryJpaEntity(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getParentId(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getDeletedAt()
        );
    }

    // JPA Entity → 도메인 변환
    Category toDomain() {
        return Category.restore(
                this.categoryId,
                this.categoryName,
                this.parentId,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}