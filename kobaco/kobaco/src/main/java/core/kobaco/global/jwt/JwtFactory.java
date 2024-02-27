package core.kobaco.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequiredArgsConstructor
public class JwtFactory {
    private final JwtProvider jwtProvider;

    public String generateAccessToken(PrivateClaims.UserClaims userClaims){
        return JwtHeaderConsts.BEARER_PREFIX + jwtProvider.generateAccessToken(userClaims);
    }
}
