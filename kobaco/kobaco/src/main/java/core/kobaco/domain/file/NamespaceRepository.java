package core.kobaco.domain.file;

import java.util.Optional;

public interface NamespaceRepository {
    Optional<Namespace> findByUserId(Long userId);

    Namespace save(Namespace namespace);
}
