package core.kobaco.domain.advertise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertisementTrendRepository {
    AdvertisementTrend save(AdvertisementTrend advertisementTrend);

    Page<AdvertisementTrend> findAll(Pageable pageable);
}
