package com.bookee.bookee.domain.user.controller;


import com.bookee.bookee.domain.user.dto.LoginRequest;
import com.bookee.bookee.domain.user.dto.LoginResponse;
import com.bookee.bookee.domain.user.dto.SignUpRequest;
import com.bookee.bookee.domain.user.dto.SignUpResponse;
import com.bookee.bookee.domain.user.service.LoginService;
import com.bookee.bookee.domain.user.service.SignUpService;
import com.bookee.bookee.global.api.ApiCommonResponse;
import com.bookee.bookee.global.api.ApiCommonResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final SignUpService signUpService;
    private final LoginService loginService;
    private final ApiCommonResponseService apiCommonResponseService;


    /**
     * 회원가입 API
     */
    @PostMapping("/signup")
    public ApiCommonResponse<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        SignUpResponse response = signUpService.signUp(request);

        return apiCommonResponseService.success(response);
    }

    /**
     * 로그인 API
     */
    @PostMapping("/login")
    public ApiCommonResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = loginService.login(request);

        return apiCommonResponseService.success(response);
    }
}