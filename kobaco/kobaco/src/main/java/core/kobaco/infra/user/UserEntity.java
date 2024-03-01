package core.kobaco.infra.user;

import core.kobaco.infra.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    private UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserEntity of(String email, String password) {
        return new UserEntity(email, password);
    }
}
