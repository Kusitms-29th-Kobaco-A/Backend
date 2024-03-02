package core.kobaco.application.advertise.service.dto;

import javax.swing.text.StyledEditorKit;

public record AdvertiseLikeDetailResponse(
    Boolean isLike,
    Long likeCount
) {
    public static AdvertiseLikeDetailResponse of(Boolean isLike, Long likeCount) {
        return new AdvertiseLikeDetailResponse(isLike, likeCount);
    }
}
