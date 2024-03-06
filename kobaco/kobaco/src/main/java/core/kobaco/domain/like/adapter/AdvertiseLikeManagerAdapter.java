package core.kobaco.domain.like.adapter;

import core.kobaco.domain.advertise.service.AdvertiseLikeManager;
import core.kobaco.domain.like.AdvertiseLike;
import core.kobaco.domain.like.AdvertiseLikeRepository;
import core.kobaco.domain.user.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertiseLikeManagerAdapter implements AdvertiseLikeManager {
    private final UserUtils userUtils;
    private final AdvertiseLikeRepository advertiseLikeRepository;

    @Override
    public Long getLikeCount(Long advertiseId) {
        return advertiseLikeRepository.countByAdvertisementId(advertiseId);
    }

    @Override
    public Boolean isLike(Long advertiseId) {
        return userUtils.isLogin()
            ? advertiseLikeRepository.isLike(advertiseId, userUtils.getRequestUserId())
            : false;
    }

    @Override
    public void like(Long advertiseId, Long userId) {
        advertiseLikeRepository.findByAdvertisementIdAndUserId(advertiseId, userId)
            .ifPresentOrElse(
                advertiseLikeRepository::delete,
                () -> advertiseLikeRepository.save(AdvertiseLike.of(null, advertiseId, userId))
        );
    }
}
