package core.kobaco.domain.advertise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertisementKeyword {
    private Long advertiseKeywordId;
    private Long advertiseId;
    private Long keywordId;

    public static AdvertisementKeyword of(Long advertiseKeywordId, Long advertiseId, Long keywordId) {
        return new AdvertisementKeyword(advertiseKeywordId, advertiseId, keywordId);
    }
}
