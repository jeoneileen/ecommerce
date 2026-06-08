package com.ecommerce.user.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserSignUpRequestDto {

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 100, message = "이메일은 100자 이내로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "휴대폰번호는 필수 입력값입니다.")
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "휴대폰번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Size(max = 100, message = "이름은 100자 이내로 입력해주세요.")
    private String userName;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }
}