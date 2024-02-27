package core.kobaco.global.jwt.exception;

import core.kobaco.global.exception.ErrorCode;


public enum AuthErrorCode implements ErrorCode {
    INVALID_TOKEN("유효하지 않는 토큰입니다.", 1000),
    EXPIRED_TOKEN("만료된 토큰입니다.", 1001),
    ;

    private final String code;
    private final String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
