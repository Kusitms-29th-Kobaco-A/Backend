package core.kobaco.global.jwt.exception;

import core.kobaco.global.exception.ErrorCode;


public enum AuthErrorCode implements ErrorCode {
    INVALID_TOKEN(1000,"유효하지 않는 토큰입니다."),
    EXPIRED_TOKEN( 1001,"만료된 토큰입니다."),
    ;

    private final int code;
    private final String message;

    AuthErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
