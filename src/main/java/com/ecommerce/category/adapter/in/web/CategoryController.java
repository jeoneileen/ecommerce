package com.ecommerce.category.adapter.in.web;

import com.ecommerce.category.adapter.in.web.dto.CategoryResponseDto;
import com.ecommerce.category.adapter.in.web.dto.CreateCategoryRequestDto;
import com.ecommerce.category.adapter.in.web.dto.UpdateCategoryRequestDto;
import com.ecommerce.category.application.port.in.*;
import com.ecommerce.category.domain.Category;
import com.ecommerce.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Category", description = "카테고리 API")
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @Operation(summary = "카테고리 등록")
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createCategory(
            @Valid @RequestBody CreateCategoryRequestDto requestDto) {
        Long categoryId = createCategoryUseCase.createCategory(requestDto.toCommand());
        return ResponseEntity.ok(ApiResponse.success("카테고리가 등록되었습니다.", categoryId));
    }

    @Operation(summary = "카테고리 단건 조회")
    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategory(
            @PathVariable Long categoryId) {
        Category category = getCategoryUseCase.getCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success("카테고리 조회 성공",
                CategoryResponseDto.fromDomain(category)));
    }

    @Operation(summary = "전체 카테고리 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategories() {
        List<CategoryResponseDto> categories = getCategoryUseCase.getAllCategories()
                .stream()
                .map(CategoryResponseDto::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("카테고리 목록 조회 성공", categories));
    }

    @Operation(summary = "하위 카테고리 조회")
    @GetMapping("/{categoryId}/children")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getChildCategories(
            @PathVariable Long categoryId) {
        List<CategoryResponseDto> children = getCategoryUseCase.getChildCategories(categoryId)
                .stream()
                .map(CategoryResponseDto::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("하위 카테고리 조회 성공", children));
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> updateCategory(
            @PathVariable Long categoryId,
            @Valid @RequestBody UpdateCategoryRequestDto requestDto) {
        updateCategoryUseCase.updateCategory(categoryId, requestDto.toCommand());
        return ResponseEntity.ok(ApiResponse.success("카테고리가 수정되었습니다."));
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(
            @PathVariable Long categoryId) {
        deleteCategoryUseCase.deleteCategory(categoryId);
        return ResponseEntity.ok(ApiResponse.success("카테고리가 삭제되었습니다."));
    }
}