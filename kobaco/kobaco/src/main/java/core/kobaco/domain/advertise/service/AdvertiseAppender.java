package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertiseAppender {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementKeywordRepository advertisementKeywordRepository;
    private final AdvertisementTrendRepository advertisementTrendRepository;

    public void append(Advertisement advertisement, List<Long> keywordIds) {
        final Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        final List<AdvertisementKeyword> advertisementKeywords = keywordIds.stream()
            .map(keywordId -> AdvertisementKeyword.of(null, savedAdvertisement.getId(), keywordId))
            .toList();
        advertisementKeywordRepository.saveAll(advertisementKeywords);
    }

    public void appendTrend(AdvertisementTrend advertisementTrend) {
        advertisementTrendRepository.save(advertisementTrend);
    }
}
