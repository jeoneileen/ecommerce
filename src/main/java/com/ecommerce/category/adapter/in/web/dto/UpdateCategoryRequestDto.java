package com.ecommerce.category.adapter.in.web.dto;

import com.ecommerce.category.application.port.in.UpdateCategoryCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequestDto(
        @NotBlank(message = "카테고리명은 필수입니다.")
        @Size(max = 100, message = "카테고리명은 100자 이하여야 합니다.")
        String categoryName
) {
    public UpdateCategoryCommand toCommand() {
        return new UpdateCategoryCommand(categoryName);
    }
}
