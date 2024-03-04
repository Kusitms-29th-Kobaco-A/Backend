package core.kobaco.domain.advertisesave.service;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertisesave.AdvertiseSave;
import core.kobaco.domain.advertisesave.AdvertiseSaveRepository;
import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import core.kobaco.domain.file.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAppender {
    private final AdvertiseSaveRepository advertiseSaveRepository;
    private final FileRepository fileRepository;

    public void append(Advertisement advertisement, File directory) {
        final File savedFile = fileRepository.save(File.of(FileType.ADVERTISE, directory.getId(), directory.getNamespaceId()));
        final AdvertiseSave advertiseSave = AdvertiseSave.of(savedFile.getId(),advertisement.getId());
        advertiseSaveRepository.save(advertiseSave);
    }
}
