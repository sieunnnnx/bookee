package com.bookee.bookee.global.exception;

import com.bookee.bookee.global.api.BaseCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final BaseCode code;

    public CustomException(BaseCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
