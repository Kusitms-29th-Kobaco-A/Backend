package core.kobaco.infra.like;

import core.kobaco.domain.like.AdvertiseLikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertiseLikeRepositoryImpl implements AdvertiseLikeRepository {
    @Override
    public Long getLikeCount(Long advertiseId) {
        return null;
    }

    @Override
    public Boolean isLike(Long advertiseId, Long userId) {
        return null;
    }
}
