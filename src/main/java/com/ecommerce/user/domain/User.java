package com.ecommerce.user.domain;

import java.time.LocalDateTime;

public class User {

    private final Long userId;
    private final String email;
    private String password;
    private final String phoneNumber;
    private final String userName;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;  // NULL이면 정상 회원, 값이 있으면 탈퇴 회원

    // 신규 회원 생성용 생성자 (userId, createdAt은 DB에서 생성)
    public User(String email, String password, String phoneNumber, String userName) {
        this.userId = null;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.deletedAt = null;
    }

    // DB 조회 결과 복원용 생성자 (전체 필드)
    public User(Long userId, String email, String password, String phoneNumber,
                String userName, LocalDateTime createdAt, LocalDateTime updatedAt,
                LocalDateTime deletedAt) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // 정상 회원 여부 확인
    public boolean isActive() {
        return this.deletedAt == null;
    }

    // 회원 탈퇴 처리
    public void withdraw() {
        this.deletedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 비밀번호 변경
    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getUserId() {
        return userId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}