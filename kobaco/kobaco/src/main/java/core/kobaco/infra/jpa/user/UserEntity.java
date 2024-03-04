package core.kobaco.infra.jpa.user;

import core.kobaco.infra.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;


    public static UserEntity of(String email, String password) {
        return new UserEntity(null, email, password);
    }

    public static UserEntity from(Long id){
        return new UserEntity(id, null, null);
    }
}
