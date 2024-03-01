package core.kobaco.domain.like;

import lombok.Getter;

@Getter
public class AdvertiseLike {
    private Long likeId;
    private Long advertiseId;
    private Long userId;

    private AdvertiseLike(Long likeId, Long advertiseId, Long userId) {
        this.likeId = likeId;
        this.advertiseId = advertiseId;
        this.userId = userId;
    }

    public static AdvertiseLike of(Long likeId, Long advertiseId, Long userId) {
        return new AdvertiseLike(likeId, advertiseId, userId);
    }
}
