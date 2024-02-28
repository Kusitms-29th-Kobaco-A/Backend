package core.kobaco.domain.user;

import java.util.Optional;

public interface UserRepository {
    Boolean existsByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

}
