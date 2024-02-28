package core.kobaco.global.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
    private long accessTokenExpirationTime;

    public JwtProperties() {

    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setAccessTokenExpirationTime(long accessTokenExpirationTime) {
        this.accessTokenExpirationTime = accessTokenExpirationTime;
    }
}
