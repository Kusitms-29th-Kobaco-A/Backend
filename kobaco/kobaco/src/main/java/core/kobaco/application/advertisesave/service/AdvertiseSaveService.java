package core.kobaco.application.advertisesave.service;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.service.AdvertiseReader;
import core.kobaco.domain.advertisesave.service.SaveAppender;
import core.kobaco.domain.file.File;
import core.kobaco.domain.file.service.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiseSaveService {
    private final SaveAppender saveAppender;
    private final FileReader fileReader;
    private final AdvertiseReader advertiseReader;

    @Transactional
    public void saveAdvertise(Long directoryId, Long advertiseId) {
        final File directory = fileReader.getDirectory(directoryId);
        final Advertisement advertisement = advertiseReader.getAdvertise(advertiseId);
        saveAppender.append(advertisement, directory);
    }
}
