package core.kobaco.domain.advertise.service;

public interface AdvertiseLikeManager {
    Long getLikeCount(Long advertiseId);

    Boolean isLike(Long advertiseId);

    void like(Long advertiseId, Long userId);
}
