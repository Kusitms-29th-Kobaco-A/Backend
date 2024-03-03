package core.kobaco.domain.user;

import core.kobaco.domain.user.service.UserReader;
import core.kobaco.global.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final JwtUtils jwtUtils;
    private final UserReader userReader;

    public User getRequestUser(){
        final Long userId = jwtUtils.getRequestUserId();
        return userReader.read(userId);
    }

    public Long getRequestUserId(){
        return getRequestUser().getId();
    }

    public Boolean isLogin(){
        return jwtUtils.isLogin();
    }
}
