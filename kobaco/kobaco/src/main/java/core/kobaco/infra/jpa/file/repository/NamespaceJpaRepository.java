package core.kobaco.infra.jpa.file.repository;

import core.kobaco.infra.jpa.file.entity.NamespaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NamespaceJpaRepository extends JpaRepository<NamespaceEntity, Long> {
    Optional<NamespaceEntity> findByUserId(Long userId);
}
