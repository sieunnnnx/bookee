package com.bookee.bookee.domain.user.dto;


import lombok.Getter;

@Getter
public class SignUpRequest {

    private String loginId;

    private String password;

    private String nickname;
}
