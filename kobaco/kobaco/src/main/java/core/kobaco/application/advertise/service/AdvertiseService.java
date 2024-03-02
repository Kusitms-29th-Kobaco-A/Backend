package core.kobaco.application.advertise.service;

import core.kobaco.application.advertise.service.dto.AdvertiseCreateRequest;
import core.kobaco.application.advertise.service.dto.AdvertiseDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseLikeDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseSaveRequest;
import core.kobaco.domain.advertise.service.AdvertiseAppender;
import core.kobaco.domain.advertise.service.AdvertiseLikeManager;
import core.kobaco.domain.advertise.service.AdvertiseReader;
import core.kobaco.domain.advertise.service.AdvertiseSaveManager;
import core.kobaco.domain.keyword.service.KeywordFactory;
import core.kobaco.domain.keyword.service.KeywordReader;
import core.kobaco.domain.user.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiseService {
    private final UserUtils userUtils;
    private final AdvertiseReader advertiseReader;
    private final AdvertiseAppender advertiseAppender;
    private final KeywordReader keywordReader;
    private final KeywordFactory keywordFactory;
    private final AdvertiseLikeManager advertiseLikeManager;
//    private final AdvertiseSaveManager advertiseSaveManager;

    @Transactional
    public void createAdvertise(AdvertiseCreateRequest request){
        final List<Long> keywordIdList = keywordFactory.upsert(request.keywordList());
        advertiseAppender.append(request.toDomain(), keywordIdList);
    }

    public void getAdvertiseList(){

    }


    public AdvertiseDetailResponse getAdvertise(final Long advertiseId) {
        return AdvertiseDetailResponse.of(
            advertiseReader.getAdvertise(advertiseId),
            advertiseReader.getAdvertiseKeyword(advertiseId)
                .stream()
                .map(keyword -> keywordReader.getKeyword(keyword.getKeywordId()).getKeyword())
                .toList()
        );
    }

    @Transactional
    public void likeAdvertise(final Long advertiseId) {
        final Long userId = userUtils.getRequestUserId();
        advertiseLikeManager.like(advertiseId, userId);
    }

    public AdvertiseLikeDetailResponse getAdvertiseLikeCount(final Long advertiseId) {
        return AdvertiseLikeDetailResponse.of(
            advertiseLikeManager.isLike(advertiseId),
            advertiseLikeManager.getLikeCount(advertiseId)
        );
    }

    @Transactional
    public void saveAdvertise(final Long advertiseId, final AdvertiseSaveRequest request) {
        final Long userId = userUtils.getRequestUserId();

    }

    @Transactional
    public void captureAdvertise(final Long advertiseId, MultipartFile multipartFile) {

    }


}
