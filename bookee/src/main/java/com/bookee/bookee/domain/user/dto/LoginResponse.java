package com.bookee.bookee.domain.user.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class LoginResponse {

    private Long userId;

    private String loginId;

    private String nickname;
}
