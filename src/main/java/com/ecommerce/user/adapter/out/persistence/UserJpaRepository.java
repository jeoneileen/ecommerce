package com.ecommerce.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    // 이메일 중복 체크
    boolean existsByEmail(String email);

    // 휴대폰번호 중복 체크
    boolean existsByPhoneNumber(String phoneNumber);

    // 이메일로 회원 조회
    Optional<UserJpaEntity> findByEmail(String email);
}