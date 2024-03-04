package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.AdvertisementKeyword;
import core.kobaco.domain.advertise.AdvertisementKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertiseKeywordReader {
    private final AdvertisementKeywordRepository advertisementKeywordRepository;

    public List<Long> getKeywordIds(Long advertiseId) {
        return advertisementKeywordRepository.findAllByAdvertiseId(advertiseId)
            .stream()
            .map(AdvertisementKeyword::getKeywordId)
            .toList();
    }
}
