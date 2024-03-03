package core.kobaco.global.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final JwtProvider jwtProvider;
    private final BearerTokenGenerator bearerTokenGenerator;

    public PrivateClaims.UserClaims parseUserClaims() {
        HttpServletRequest request =
            ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String tokenWithBearer = request.getHeader(JwtHeaderConsts.AUTHORIZATION_HEADER);
        String token = bearerTokenGenerator.detachBearerPrefix(tokenWithBearer);
        return jwtProvider.extractUserClaimsFromToken(token, TokenType.ACCESS_TOKEN);
    }

    public Long getRequestUserId(){
        return parseUserClaims().getUserId();
    }

    public Boolean isLogin(){
        try{
            parseUserClaims();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
