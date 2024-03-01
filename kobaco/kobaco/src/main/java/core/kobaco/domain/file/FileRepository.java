package core.kobaco.domain.file;

import java.util.List;
import java.util.Optional;

public interface FileRepository {

    Optional<File> findRootDirectoryByNamespaceId(Long namespaceId);

    File save(File file);

    List<File> findAllByFileId(Long fileId);

    Optional<File> findByFileId(Long directoryId);

    void update(File file);
}
