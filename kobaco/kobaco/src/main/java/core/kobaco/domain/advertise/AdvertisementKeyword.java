package core.kobaco.domain.advertise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class AdvertisementKeyword {
    private Long advertiseKeywordId;
    private Long advertiseId;
    private Long keywordId;

    private AdvertisementKeyword(Long advertiseKeywordId, Long advertiseId, Long keywordId) {
        this.advertiseKeywordId = advertiseKeywordId;
        this.advertiseId = advertiseId;
        this.keywordId = keywordId;
    }

    public static AdvertisementKeyword of(Long advertiseKeywordId, Long advertiseId, Long keywordId) {
        return new AdvertisementKeyword(advertiseKeywordId, advertiseId, keywordId);
    }
}
