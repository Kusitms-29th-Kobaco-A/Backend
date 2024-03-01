package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertiseReader {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementKeywordRepository advertisementKeywordRepository;

    public Advertisement getAdvertise(Long advertiseId) {
        return advertisementRepository.findById(advertiseId)
            .orElseThrow(() -> new IllegalArgumentException("해당 광고가 존재하지 않습니다."));
    }

    public List<AdvertisementKeyword> getAdvertiseKeyword(Long advertiseId) {
        return advertisementKeywordRepository.findAllByAdvertiseId(advertiseId);
    }

}
