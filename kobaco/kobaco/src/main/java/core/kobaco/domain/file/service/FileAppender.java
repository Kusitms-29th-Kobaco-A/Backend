package core.kobaco.domain.file.service;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileAppender {
    private final FileRepository fileRepository;

    public void append(File file){
        fileRepository.save(file);
    }
}
