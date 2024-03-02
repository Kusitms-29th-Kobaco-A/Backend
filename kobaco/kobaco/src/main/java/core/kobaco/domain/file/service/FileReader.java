package core.kobaco.domain.file.service;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileReader {
    private final FileRepository fileRepository;

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

}
