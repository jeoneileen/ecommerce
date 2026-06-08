package com.ecommerce.user.application.port.out;

import com.ecommerce.user.domain.User;

import java.util.Optional;

public interface SaveUserPort {

    // 회원 저장
    User saveUser(User user);

    // 이메일 중복 체크
    boolean existsByEmail(String email);

    // 휴대폰번호 중복 체크
    boolean existsByPhoneNumber(String phoneNumber);
}