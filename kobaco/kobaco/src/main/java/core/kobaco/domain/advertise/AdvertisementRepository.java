package core.kobaco.domain.advertise;

import java.util.Optional;

public interface AdvertisementRepository {
    Optional<Advertisement> findById(Long advertiseId);

    Advertisement save(Advertisement advertisement);
}
