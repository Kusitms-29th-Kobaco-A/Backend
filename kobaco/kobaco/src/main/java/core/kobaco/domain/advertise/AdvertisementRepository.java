package core.kobaco.domain.advertise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdvertisementRepository {
    Optional<Advertisement> findById(Long advertiseId);

    Advertisement save(Advertisement advertisement);

    Page<Advertisement> findAll(Pageable pageable);
}
