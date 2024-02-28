package core.kobaco.domain.user;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String email;
    private String password;

    private User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static User of(Long userId, String email, String password) {
        return new User(userId, email, password);
    }
}
