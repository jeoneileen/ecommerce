package com.ecommerce.user.application.port.out;

import com.ecommerce.user.domain.User;

import java.util.Optional;

public interface LoadUserPort {

    Optional<User> loadByEmail(String email);
}
