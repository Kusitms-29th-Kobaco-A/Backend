package core.kobaco.domain.like;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AdvertiseLikeRepository {
    Long countByAdvertisementId(Long advertiseId);

    Boolean isLike(Long advertiseId, Long userId);

    AdvertiseLike save(AdvertiseLike advertiseLike);

    void delete(AdvertiseLike advertiseLike);

    Optional<AdvertiseLike> findByAdvertisementIdAndUserId(Long advertiseId, Long userId);

    List<Long> findTopLankAdvertiseId(Pageable pageable);
}
