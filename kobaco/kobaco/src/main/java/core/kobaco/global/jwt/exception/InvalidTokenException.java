package core.kobaco.global.jwt.exception;

import core.kobaco.global.exception.ErrorCode;

public class InvalidTokenException extends TokenException{

    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
