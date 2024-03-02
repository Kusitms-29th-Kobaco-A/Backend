package core.kobaco.domain.like;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AdvertiseLikeRepository {
    Long countByAdvertisementId(Long advertiseId);

    Boolean isLike(Long advertiseId, Long userId);

    AdvertiseLike save(AdvertiseLike advertiseLike);

    void delete(AdvertiseLike advertiseLike);

    Optional<AdvertiseLike> findByAdvertisementIdAndUserId(Long advertiseId, Long userId);
}
