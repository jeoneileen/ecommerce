package com.ecommerce.user.adapter.out.persistence;

import com.ecommerce.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Comment("사용자ID")
    private Long userId;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Comment("이메일주소(로그인ID)")
    private String email;

    @Column(name = "password", length = 200, nullable = false)
    @Comment("로그인 비밀번호")
    private String password;

    @Column(name = "phone_number", length = 20, nullable = false, unique = true)
    @Comment("사용자 휴대폰번호")
    private String phoneNumber;

    @Column(name = "user_name", length = 100, nullable = false)
    @Comment("사용자이름")
    private String userName;

    @Column(name = "created_at", nullable = false)
    @Comment("가입일시")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment("수정일시")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    @Comment("탈퇴일시, NULL이면 정상 회원")
    private LocalDateTime deletedAt;  // NULL이면 정상 회원, 값이 있으면 탈퇴 회원

    private UserJpaEntity(String email, String password, String phoneNumber,
                          String userName, LocalDateTime createdAt,
                          LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // 도메인 객체 → JPA 엔티티 변환
    static UserJpaEntity fromDomain(User user) {
        return new UserJpaEntity(
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getUserName(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    // JPA 엔티티 → 도메인 객체 변환
    User toDomain() {
        return new User(
                this.userId,
                this.email,
                this.password,
                this.phoneNumber,
                this.userName,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}