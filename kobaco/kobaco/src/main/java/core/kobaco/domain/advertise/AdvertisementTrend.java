package core.kobaco.domain.advertise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertisementTrend {
    private Long id;
    private Long advertiseId;

    public static AdvertisementTrend of(Long bestAdvertiseId, Long advertiseId) {
        return new AdvertisementTrend(bestAdvertiseId, advertiseId);
    }

    public static AdvertisementTrend from(Long advertiseId){
        return new AdvertisementTrend(null, advertiseId);
    }
}
