package core.kobaco.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BearerTokenGenerator {
    private final JwtProvider jwtProvider;

    public String generateAccessTokenAndAttachBearer(PrivateClaims.UserClaims userClaims){
        return JwtHeaderConsts.BEARER_PREFIX + jwtProvider.generateAccessToken(userClaims);
    }
    
    public String detachBearerPrefix(String token){
        return token.replace(JwtHeaderConsts.BEARER_PREFIX, "");
    }

}