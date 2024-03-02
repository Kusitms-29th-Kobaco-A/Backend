package core.kobaco.infra.jpa.file.repository;

import core.kobaco.infra.jpa.file.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FileJpaRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByNamespaceId(Long namespaceId);

    @Query("""
    select f
        from FileEntity f
        where f.namespace.id = :namespaceId
        and f.parentFile is null
    """)
    Optional<FileEntity> findRootDirectoryByNamespaceId(Long namespaceId);

    List<FileEntity> findAllByParentFileId(Long fileId);
}
