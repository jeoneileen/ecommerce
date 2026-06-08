package com.ecommerce.common;

public class ApiResponse<Type> {

    private final boolean success;
    private final String message;
    private final Type data;

    private ApiResponse(boolean success, String message, Type data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <Type> ApiResponse<Type> success(String message, Type data) {
        return new ApiResponse<>(true, message, data);
    }

    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static <Type> ApiResponse<Type> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Type getData() {
        return data;
    }
}
