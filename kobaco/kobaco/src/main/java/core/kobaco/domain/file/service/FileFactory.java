package core.kobaco.domain.file.service;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import core.kobaco.domain.file.Namespace;
import core.kobaco.domain.file.NamespaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileFactory {
    private final NamespaceRepository namespaceRepository;
    private final FileRepository fileRepository;

    private Namespace upsert(final Long userId) {
        return namespaceRepository.findByUserId(userId)
            .orElseGet(() -> namespaceRepository.save(Namespace.from(userId)));
    }

    public File createRootDirectory(final Long userId){
        Namespace namespace = upsert(userId);
        return fileRepository.findRootDirectoryByNamespaceId(namespace.getNamespaceId())
            .orElseGet(() -> fileRepository.save(File.rootDirectory(namespace.getNamespaceId())));
    }
}
