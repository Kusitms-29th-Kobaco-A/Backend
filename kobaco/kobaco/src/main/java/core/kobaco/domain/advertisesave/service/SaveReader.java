package core.kobaco.domain.advertisesave.service;

import core.kobaco.domain.advertisesave.AdvertiseSave;
import core.kobaco.domain.advertisesave.AdvertiseSaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveReader {
    private final AdvertiseSaveRepository advertiseSaveRepository;


    public AdvertiseSave getAdvertiseSave(final Long fileId) {
        return advertiseSaveRepository.findByFileId(fileId)
                .orElseThrow(() -> new IllegalArgumentException("AdvertiseSave not found"));
    }
}
