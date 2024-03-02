package core.kobaco.infra.jpa.keyword;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordJpaRepository extends JpaRepository<KeywordEntity, Long> {
    Optional<KeywordEntity> findByDescription(String description);
}
