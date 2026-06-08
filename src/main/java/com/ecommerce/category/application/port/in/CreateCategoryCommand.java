package com.ecommerce.category.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryCommand(
        @NotBlank(message = "카테고리명은 필수입니다.")
        @Size(max = 100, message = "카테고리명은 100자 이하여야 합니다.")
        String categoryName,
        Long parentId   // 최상위 카테고리면 null
) {}