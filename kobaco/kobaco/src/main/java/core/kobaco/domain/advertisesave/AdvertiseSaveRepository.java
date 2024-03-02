package core.kobaco.domain.advertisesave;

import java.util.Optional;

public interface AdvertiseSaveRepository {
    AdvertiseSave save(AdvertiseSave advertiseSave);

    Optional<AdvertiseSave> findByFileId(Long fileId);
}
