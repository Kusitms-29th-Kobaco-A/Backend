package core.kobaco.global.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.channels.AcceptPendingException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtHeaderConsts {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
}
