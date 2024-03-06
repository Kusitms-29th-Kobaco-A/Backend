package core.kobaco.domain.file.service;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import core.kobaco.domain.file.Namespace;
import core.kobaco.domain.file.NamespaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileReader {
    private final FileRepository fileRepository;
    private final NamespaceRepository namespaceRepository;

    public List<File> getDirectoryFiles(final Long directoryId){
        return fileRepository.findAllByFileId(directoryId);
    }

    public File getFile(final Long directoryId){
        return fileRepository.findByFileId(directoryId)
                .orElseThrow(() -> new IllegalArgumentException("Directory not found"));
    }

    public File getCaptureDirectoryByUserId(final Long userId){
        return fileRepository.findCaptureDirectoryByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Capture directory not found"));
    }

    public Namespace getNamespaceByUserId(final Long userId){
        return namespaceRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Namespace not found"));
    }

}
