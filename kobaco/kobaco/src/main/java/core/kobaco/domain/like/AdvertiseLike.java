package core.kobaco.domain.like;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertiseLike {
    private Long likeId;
    private Long advertiseId;
    private Long userId;

    public static AdvertiseLike of(Long likeId, Long advertiseId, Long userId) {
        return new AdvertiseLike(likeId, advertiseId, userId);
    }
}
