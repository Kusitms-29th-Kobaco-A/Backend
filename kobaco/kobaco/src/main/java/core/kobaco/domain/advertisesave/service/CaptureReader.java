package core.kobaco.domain.advertisesave.service;

import core.kobaco.domain.advertisesave.AdvertiseCapture;
import core.kobaco.domain.advertisesave.AdvertiseCaptureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaptureReader {
    private final AdvertiseCaptureRepository advertiseCaptureRepository;

    public AdvertiseCapture getAdvertiseCapture(final Long fileId) {
        return advertiseCaptureRepository.findByFileId(fileId)
                .orElseThrow(() -> new IllegalArgumentException("AdvertiseCapture not found"));
    }
}
