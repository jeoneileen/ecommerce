package com.ecommerce.category.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {

    // 삭제되지 않은 전체 카테고리 조회
    List<CategoryJpaEntity> findAllByDeletedAtIsNull();

    // 삭제되지 않은 하위 카테고리 조회
    List<CategoryJpaEntity> findByParentIdAndDeletedAtIsNull(Long parentId);
}