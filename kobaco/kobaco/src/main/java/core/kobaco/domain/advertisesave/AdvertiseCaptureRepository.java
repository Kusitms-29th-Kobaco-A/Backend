package core.kobaco.domain.advertisesave;

import java.util.Optional;

public interface AdvertiseCaptureRepository {
    AdvertiseCapture save(AdvertiseCapture advertiseCapture);

    Optional<AdvertiseCapture> findByFileId(Long fileId);
}
