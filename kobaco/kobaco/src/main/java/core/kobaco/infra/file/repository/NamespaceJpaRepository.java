package core.kobaco.infra.file.repository;

import core.kobaco.infra.file.entity.NamespaceEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NamespaceJpaRepository extends JpaRepository<NamespaceEntity, Long> {
    Optional<NamespaceEntity> findByUserId(Long userId);
}
