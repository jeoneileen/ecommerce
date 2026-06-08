package com.ecommerce.user.adapter.in.web;

import com.ecommerce.common.ApiResponse;
import com.ecommerce.common.ApiUrl;
import com.ecommerce.user.adapter.in.web.dto.TokenResponseDto;
import com.ecommerce.user.adapter.in.web.dto.UserLoginRequestDto;
import com.ecommerce.user.adapter.in.web.dto.UserSignUpRequestDto;
import com.ecommerce.user.application.port.in.LoginCommand;
import com.ecommerce.user.application.port.in.LoginUseCase;
import com.ecommerce.user.application.port.in.SignUpCommand;
import com.ecommerce.user.application.port.in.SignUpUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;

    @Operation(summary = "회원가입", description = "이메일, 비밀번호, 전화번호를 기준으로 신규유저를 등록합니다.")
    @PostMapping(ApiUrl.SIGN_UP)
    public ResponseEntity<ApiResponse<Long>> signUp(@RequestBody @Valid UserSignUpRequestDto request) {

        SignUpCommand command = new SignUpCommand(
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getUserName()
        );

        Long userId = signUpUseCase.signUp(command);

        return ResponseEntity.ok(ApiResponse.success("회원가입에 성공했습니다.", userId));
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인 후 JWT accessToken을 반환합니다.")
    @PostMapping(ApiUrl.LOGIN)
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(@RequestBody @Valid UserLoginRequestDto request) {

        LoginCommand command = new LoginCommand(request.getEmail(), request.getPassword());

        String token = loginUseCase.login(command);

        return ResponseEntity.ok(ApiResponse.success("로그인에 성공했습니다.", new TokenResponseDto(token)));
    }
}