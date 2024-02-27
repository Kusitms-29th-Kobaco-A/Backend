package core.kobaco.infra.user;

import core.kobaco.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user){
        return UserEntity.of(
            user.getEmail(),
            user.getPassword()
        );
    }

    public User toDomain(UserEntity userEntity){
        return User.of(
            userEntity.getId(),
            userEntity.getEmail(),
            userEntity.getPassword()
        );
    }
}
