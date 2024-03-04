package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementKeyword;
import core.kobaco.domain.advertise.AdvertisementKeywordRepository;
import core.kobaco.domain.advertise.AdvertisementRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Advertisement> getAllAdvertiseList(Pageable pageable, @Nullable List<String> keywordList) {
//        return advertisementRepository.findAll(pageable);
        return advertisementRepository.findAllWithKeyword(pageable, keywordList);
    }

    public Page<Advertisement> getSaveAdvertiseList(Long requestUserId, Pageable pageable) {
        return advertisementRepository.findSavedAllByUserId(pageable, requestUserId);
    }
}
