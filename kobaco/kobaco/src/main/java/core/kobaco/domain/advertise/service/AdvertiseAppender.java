package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementKeyword;
import core.kobaco.domain.advertise.AdvertisementKeywordRepository;
import core.kobaco.domain.advertise.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertiseAppender {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementKeywordRepository advertisementKeywordRepository;

    public void append(Advertisement advertisement, List<Long> keywordIds) {
        final Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        final List<AdvertisementKeyword> advertisementKeywords = keywordIds.stream()
            .map(keywordId -> AdvertisementKeyword.of(null, savedAdvertisement.getAdvertiseId(), keywordId))
            .toList();
        advertisementKeywordRepository.saveAll(advertisementKeywords);
    }
}
