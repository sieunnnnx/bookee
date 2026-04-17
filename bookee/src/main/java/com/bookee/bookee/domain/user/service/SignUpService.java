package com.bookee.bookee.domain.user.service;

import com.bookee.bookee.domain.user.dto.SignUpRequest;
import com.bookee.bookee.domain.user.dto.SignUpResponse;
import com.bookee.bookee.domain.user.entity.User;
import com.bookee.bookee.domain.user.exception.UserException;
import com.bookee.bookee.domain.user.repository.UserRepository;
import com.bookee.bookee.global.exception.CustomException;
import com.bookee.bookee.global.util.NicknameGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {

        if (userRepository.existsByLoginId(request.getLoginId()))
            throw new CustomException(UserException.LOGIN_IN_ALREADY_EXIST);

        String nickname = request.getNickname();

        if (nickname == null || nickname.isBlank())
            nickname = generateUniqueNickname();
        else
            if (userRepository.existsByNickname(nickname))
                throw new CustomException(UserException.NICKNAME_ALREADY_EXIST);

        User user = User.signUp(
                request.getLoginId(),
                request.getPassword(),
                nickname
        );

        User newUser = userRepository.save(user);

        return SignUpResponse.builder()
                .userId(newUser.getId())
                .loginId(newUser.getLoginId())
                .nickname(newUser.getNickname())
                .build();

    }

    /**
     * 닉네임 중복 방지 생성
     */
    private String generateUniqueNickname() {
        String nickname;

        do {
            nickname = NicknameGenerator.generate();
        } while (userRepository.existsByNickname(nickname));

        return nickname;
    }
}
