package core.kobaco.domain.advertise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertisementTrend {
    private Long id;
    private Long advertiseId;
    private String title;

    public static AdvertisementTrend of(Long bestAdvertiseId, Long advertiseId, String title) {
        return new AdvertisementTrend(bestAdvertiseId, advertiseId, title);
    }

    public static AdvertisementTrend of(Long advertiseId, String title) {
        return new AdvertisementTrend(null, advertiseId, title);
    }
}
