package com.bookee.bookee.domain.user.service;

import com.bookee.bookee.domain.user.dto.LoginRequest;
import com.bookee.bookee.domain.user.dto.LoginResponse;
import com.bookee.bookee.domain.user.entity.User;
import com.bookee.bookee.domain.user.exception.UserException;
import com.bookee.bookee.domain.user.repository.UserRepository;
import com.bookee.bookee.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new CustomException(UserException.LOGIN_FAILED));

        if (!user.getPassword().equals(request.getPassword()))
            throw new CustomException(UserException.LOGIN_FAILED);

        return LoginResponse.builder()
                .userId(user.getId())
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .build();
    }
}
