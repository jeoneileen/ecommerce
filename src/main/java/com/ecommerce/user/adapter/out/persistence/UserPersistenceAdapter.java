package com.ecommerce.user.adapter.out.persistence;

import com.ecommerce.user.application.port.out.LoadUserPort;
import com.ecommerce.user.application.port.out.SaveUserPort;
import com.ecommerce.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, LoadUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User saveUser(User user) {
        UserJpaEntity userJpaEntity = UserJpaEntity.fromDomain(user);
        UserJpaEntity savedEntity = userJpaRepository.save(userJpaEntity);
        return savedEntity.toDomain();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userJpaRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<User> loadByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserJpaEntity::toDomain);
    }
}