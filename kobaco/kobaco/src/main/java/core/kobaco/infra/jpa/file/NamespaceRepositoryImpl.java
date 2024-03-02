package core.kobaco.infra.jpa.file;

import core.kobaco.domain.file.Namespace;
import core.kobaco.domain.file.NamespaceRepository;
import core.kobaco.infra.jpa.file.repository.NamespaceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NamespaceRepositoryImpl implements NamespaceRepository {
    private final NamespaceJpaRepository namespaceJpaRepository;
    private final FileMapper fileMapper;
    @Override
    public Optional<Namespace> findByUserId(Long userId) {
        return namespaceJpaRepository.findByUserId(userId)
            .map(fileMapper::toDomain);
    }

    @Override
    public Namespace save(Namespace namespace) {
        return fileMapper.toDomain(namespaceJpaRepository.save(fileMapper.toEntity(namespace)));
    }
}
