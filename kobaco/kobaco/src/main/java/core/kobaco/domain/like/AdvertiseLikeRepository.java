package core.kobaco.domain.like;

public interface AdvertiseLikeRepository {
    Long getLikeCount(Long advertiseId);

    Boolean isLike(Long advertiseId, Long userId);
}
