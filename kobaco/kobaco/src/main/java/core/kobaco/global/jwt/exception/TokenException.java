package core.kobaco.global.jwt.exception;

import core.kobaco.global.exception.BusinessException;
import core.kobaco.global.exception.ErrorCode;

import java.nio.Buffer;

public class TokenException extends BusinessException {
    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
