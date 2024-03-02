package core.kobaco.domain.file.service;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileModifier {
    private final FileRepository fileRepository;
    private final FileReader fileReader;

    public void updateDirectory(final Long directoryId, final String directoryName){
        File directory = fileReader.getDirectory(directoryId);
        if(directory.isRootDirectory()){
            throw new IllegalArgumentException("Root directory name cannot be updated");
        }
        directory.updateDirectoryName(directoryName);
        fileRepository.update(directory);
    }
}
