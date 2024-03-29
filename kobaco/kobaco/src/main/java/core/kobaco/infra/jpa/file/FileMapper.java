package core.kobaco.infra.jpa.file;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.Namespace;
import core.kobaco.infra.jpa.file.entity.FileEntity;
import core.kobaco.infra.jpa.file.entity.NamespaceEntity;
import core.kobaco.infra.jpa.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileEntity toEntity(File file) {
        return FileEntity.of(
            file.getId(),
            file.getFileName(),
            file.getFileType(),
            file.getParentFileId() != null ? FileEntity.from(file.getParentFileId()) : null,
            file.getNamespaceId() != null ? NamespaceEntity.from(file.getNamespaceId()) : null
        );
    }

    public File toDomain(FileEntity fileEntity) {
        return File.of(
            fileEntity.getId(),
            fileEntity.getFileName(),
            fileEntity.getFileType(),
            fileEntity.getParentFile() != null ? fileEntity.getParentFile().getId() : null,
            fileEntity.getNamespace() != null ? fileEntity.getNamespace().getId() : null
        );
    }

    public NamespaceEntity toEntity(Namespace namespace) {
        return NamespaceEntity.of(namespace.getId(), UserEntity.from(namespace.getUserId()));
    }

    public Namespace toDomain(NamespaceEntity namespaceEntity) {
        return Namespace.of(namespaceEntity.getId(), namespaceEntity.getUser().getId());
    }
}
