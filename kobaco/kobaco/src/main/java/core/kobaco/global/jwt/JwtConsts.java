package core.kobaco.global.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtConsts {
    public static final String TOKEN_ISSUER = "kobaco";
    public static final String USER_CLAIMS = "user_claims";
    public static final String TOKEN_TYPE = "token_type";
}
