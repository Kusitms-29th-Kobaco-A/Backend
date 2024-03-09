package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.OrderType;
import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementKeyword;
import core.kobaco.domain.advertise.AdvertisementKeywordRepository;
import core.kobaco.domain.advertise.AdvertisementRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

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

    public Page<Advertisement> getAllAdvertiseList(Pageable pageable,
                                                   @Nullable List<String> keywordList,
                                                   OrderType orderType) {
//        return advertisementRepository.findAll(pageable);
        return advertisementRepository.findAllWithKeyword(pageable, keywordList, orderType);
    }

    public Page<Advertisement> getAllTopViewAdvertiseList(Pageable pageable, List<String> keywordList) {
        return advertisementRepository.findAllOrderByViewCount(pageable, keywordList);
    }

    public Page<Advertisement> getSaveAdvertiseList(Long requestUserId, Pageable pageable) {
        return advertisementRepository.findSavedAllByUserId(pageable, requestUserId);
    }

    public Page<Advertisement> getRecommendAdvertiseList(Pageable pageable, Advertisement advertisement) {
        final Page<Advertisement> recommendAdvertisementListWithKeyword = advertisementRepository.findAllWithKeywordByAdvertiseId(pageable, advertisement.getId());
        if(!recommendAdvertisementListWithKeyword.hasNext()){
            final long totalElements = recommendAdvertisementListWithKeyword.getTotalElements();
            final Page<Advertisement> makerCompanyAdvertise = advertisementRepository.findAllByMakerCompanyAndAdvertiseId(
                calculatePageable(pageable, totalElements),
                advertisement.getAdvertisementDetail().getMakerCompany(),
                advertisement.getId());
            final List<Advertisement> totalAdvertise = Stream.of(recommendAdvertisementListWithKeyword, makerCompanyAdvertise)
                .flatMap(page -> page.get().toList().stream())
                .toList();
            return new PageImpl<>(totalAdvertise, pageable, pageable.getOffset());
        }
        return recommendAdvertisementListWithKeyword;
    }

    private Pageable calculatePageable(Pageable pageable, long totalElements) {
        int currentRequestSize = pageable.getPageSize()*(pageable.getPageNumber()+1);
        long page = (currentRequestSize-totalElements)/pageable.getPageSize();
        long size = (currentRequestSize-totalElements)%pageable.getPageSize();
        return PageRequest.of((int)page, (int)size);
    }
}
