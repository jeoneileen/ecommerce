package com.ecommerce.user.application.service;

import com.ecommerce.common.jwt.JwtTokenProvider;
import com.ecommerce.user.application.port.in.LoginCommand;
import com.ecommerce.user.application.port.in.LoginUseCase;
import com.ecommerce.user.application.port.in.SignUpCommand;
import com.ecommerce.user.application.port.in.SignUpUseCase;
import com.ecommerce.user.application.port.out.LoadUserPort;
import com.ecommerce.user.application.port.out.SaveUserPort;
import com.ecommerce.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements SignUpUseCase, LoginUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public Long signUp(SignUpCommand command) {

        // 이메일 중복 체크
        if (saveUserPort.existsByEmail(command.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 휴대폰번호 중복 체크
        if (saveUserPort.existsByPhoneNumber(command.getPhoneNumber())) {
            throw new IllegalArgumentException("이미 사용 중인 휴대폰번호입니다.");
        }

        // 비밀번호 암호화 후 도메인 객체 생성
        String encodedPassword = passwordEncoder.encode(command.getPassword());
        User user = new User(
                command.getEmail(),
                encodedPassword,
                command.getPhoneNumber(),
                command.getUserName()
        );

        // 저장 후 생성된 userId 반환
        User savedUser = saveUserPort.saveUser(user);

        return savedUser.getUserId();
    }

    @Transactional(readOnly = true)
    @Override
    public String login(LoginCommand command) {

        User user = loadUserPort.loadByEmail(command.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!user.isActive()) {
            throw new IllegalArgumentException("탈퇴한 회원입니다.");
        }

        if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        return jwtTokenProvider.createToken(user.getUserId());
    }
}