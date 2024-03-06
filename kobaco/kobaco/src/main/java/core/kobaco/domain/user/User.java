package core.kobaco.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long id;
    private String email;
    private String password;

    public static User of(Long userId, String email, String password) {
        return new User(userId, email, password);
    }
}
