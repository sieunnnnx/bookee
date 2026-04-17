package com.bookee.bookee.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String loginId;
    private String password;
}
