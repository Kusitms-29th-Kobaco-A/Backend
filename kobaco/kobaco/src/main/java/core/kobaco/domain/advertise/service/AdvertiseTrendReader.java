package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.AdvertisementTrend;
import core.kobaco.domain.advertise.AdvertisementTrendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertiseTrendReader {
    private final AdvertisementTrendRepository advertisementTrendRepository;

    public Page<AdvertisementTrend> getTrendList(Pageable pageable) {
        return advertisementTrendRepository.findAll(pageable);
    }
}
