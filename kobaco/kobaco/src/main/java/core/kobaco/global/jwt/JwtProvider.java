package core.kobaco.global.jwt;


import core.kobaco.global.jwt.exception.AuthErrorCode;
import core.kobaco.global.jwt.exception.ExpiredTokenException;
import core.kobaco.global.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SignatureException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;

    public String generateAccessToken(final PrivateClaims.UserClaims userClaims) {
        return generateToken(userClaims.createPrivateClaims(TokenType.ACCESS_TOKEN), jwtProperties.getAccessTokenExpirationTime());
    }

    public PrivateClaims.UserClaims extractUserClaimsFromToken(String token, TokenType tokenType) {
        return initializeJwtParser(tokenType)
            .parseSignedClaims(token)
            .getPayload()
            .get(JwtConsts.USER_CLAIMS, PrivateClaims.UserClaims.class);
    }

    private String generateToken(final PrivateClaims privateClaims, final Long expirationTime) {
        final Date now = new Date();
        return Jwts.builder()
            .issuer(JwtConsts.TOKEN_ISSUER)
            .claims(privateClaims.createClaimsMap())
            .issuedAt(now)
            .expiration(new Date(now.getTime() + expirationTime))
            .signWith(getSigningKey())
            .compact();
    }

    /**
     * @return 서명에 사용할 Key 반환
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }

    /**
     * @throws InvalidTokenException 잘못된 토큰이 요청되었을 때 반환(서명 오류, 잘못된 토큰 형식, 잘못된 토큰 발급자, null이거나 공백인 경우)
     * @throws ExpiredTokenException 만료된 토큰이 요청되었을 때 반환
     */
    public void validateToken(final String token, final TokenType tokenType) {
        final JwtParser jwtParser = initializeJwtParser(tokenType);
        try {
            jwtParser.parse(token);
        } catch (MalformedJwtException | IncorrectClaimException | IllegalArgumentException e) {
            throw new InvalidTokenException(AuthErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException(AuthErrorCode.EXPIRED_TOKEN);
        }
    }


    private JwtParser initializeJwtParser(final TokenType tokenType) {
        return Jwts.parser()
            .json(new JacksonDeserializer<>(PrivateClaims.getClaimsTypeDetailMap()))
            .verifyWith(getSigningKey())
            .requireIssuer(JwtConsts.TOKEN_ISSUER)
            .require(JwtConsts.TOKEN_TYPE, tokenType)
            .build();
    }
}
