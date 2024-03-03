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
        File directory = fileReader.getFile(directoryId);
        if(!directory.isModifiable()){
            throw new IllegalArgumentException("directory name cannot be updated");
        }
        directory.updateDirectoryName(directoryName);
        fileRepository.update(directory);
    }

    public void moveDirectory(Long directoryId, Long targetDirectoryId) {
        File directory = fileReader.getFile(directoryId);
        File targetDirectory = fileReader.getFile(targetDirectoryId);
        directory.move(targetDirectory);
        fileRepository.update(directory);
    }

    public void deleteDirectory(Long directoryId) {
        File directory = fileReader.getFile(directoryId);
        if(!directory.isModifiable()){
            throw new IllegalArgumentException("Root directory cannot be deleted");
        }
        fileRepository.delete(directory);
    }
}
