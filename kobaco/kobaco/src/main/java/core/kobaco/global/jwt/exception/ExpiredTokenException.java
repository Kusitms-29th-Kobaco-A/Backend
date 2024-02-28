package core.kobaco.global.jwt.exception;

import core.kobaco.global.exception.ErrorCode;

public class ExpiredTokenException extends TokenException {
    public ExpiredTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
