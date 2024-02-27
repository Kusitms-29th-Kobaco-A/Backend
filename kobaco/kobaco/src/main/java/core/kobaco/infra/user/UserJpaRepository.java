package core.kobaco.infra.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmailAndPassword(String email, String password);
    Optional<UserEntity> findByEmail(String email);
}
