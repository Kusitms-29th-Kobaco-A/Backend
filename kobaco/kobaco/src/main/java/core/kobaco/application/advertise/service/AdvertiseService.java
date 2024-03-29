package core.kobaco.application.advertise.service;

import core.kobaco.application.advertise.service.dto.*;
import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementTrend;
import core.kobaco.domain.advertise.OrderType;
import core.kobaco.domain.advertise.service.*;
import core.kobaco.domain.keyword.Keyword;
import core.kobaco.domain.keyword.service.KeywordFactory;
import core.kobaco.domain.keyword.service.KeywordReader;
import core.kobaco.domain.like.service.AdvertiseLikeReader;
import core.kobaco.domain.user.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiseService {
    private final UserUtils userUtils;
    private final AdvertiseReader advertiseReader;
    private final AdvertiseAppender advertiseAppender;
    private final AdvertiseModifier advertiseModifier;
    private final KeywordReader keywordReader;
    private final KeywordFactory keywordFactory;
    private final AdvertiseLikeManager advertiseLikeManager;
    private final AdvertiseLikeReader advertiseLikeReader;
    private final AdvertiseKeywordReader advertiseKeywordReader;
    private final AdvertiseTrendReader advertiseTrendReader;


    @Transactional
    public void createAdvertise(AdvertiseCreateRequest request) {
        final List<Long> keywordIdList = keywordFactory.upsert(request.keywordList());
        advertiseAppender.append(request.toDomain(), keywordIdList);
    }


    public AdvertiseDetailResponse getAdvertise(final Long advertiseId) {
        advertiseModifier.upViewCount(advertiseId);
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


    public Page<AdvertiseSimpleResponse> getSaveAdvertiseList(Pageable pageable) {
        if (!userUtils.isLogin())
            return Page.empty();
        return advertiseReader.getSaveAdvertiseList(userUtils.getRequestUserId(), pageable)
            .map(this::convertSimpleResponse);
    }

    public Page<AdvertiseSimpleResponse> getLikeAdvertiseList(Pageable pageable, List<String> keywordList) {
        return advertiseReader.getAllTopViewAdvertiseList(pageable, keywordList)
            .map(this::convertSimpleResponse);
    }

    public Page<AdvertiseSimpleResponse> getAdvertiseList(Pageable pageable, List<String> keywordList, OrderType orderType) {
        return advertiseReader.getAllAdvertiseList(pageable, keywordList, orderType)
            .map(this::convertSimpleResponse);
    }



    public Page<AdvertiseSimpleResponse> getRecommendAdvertiseList(Pageable pageable, Long advertiseId) {
        final Advertisement advertisement = advertiseReader.getAdvertise(advertiseId);
        return advertiseReader.getRecommendAdvertiseList(pageable, advertisement)
            .map(this::convertSimpleResponse);
    }

    public Page<TrendAdvertiseSimpleResponse> getTrendAdvertiseList(Pageable pageable) {
        return advertiseTrendReader.getTrendList(pageable)
            .map(advertiseTrend -> {
                Advertisement advertisement = advertiseReader.getAdvertise(advertiseTrend.getAdvertiseId());
                List<String> advertiseKeywordList = getKeywordList(advertisement);
                return TrendAdvertiseSimpleResponse.of(
                    AdvertiseSimpleResponse.of(advertisement, advertiseKeywordList),
                    advertiseTrend.getTitle()
                );
            });
    }

    @Transactional
    public void trendAdvertise(Long advertiseId, AdvertiseTrendCreateRequest request) {
        advertiseAppender.appendTrend(AdvertisementTrend.of(advertiseId, request.title()));
    }


    private AdvertiseSimpleResponse convertSimpleResponse(Advertisement advertise) {
        List<String> advertiseKeywordList = getKeywordList(advertise);
        return AdvertiseSimpleResponse.of(advertise, advertiseKeywordList);
    }

    private List<String> getKeywordList(Advertisement advertise) {
        final List<Long> keywordIds = advertiseKeywordReader.getKeywordIds(advertise.getId());
        return keywordIds.stream()
            .map(keywordReader::getKeyword)
            .map(Keyword::getKeyword)
            .toList();
    }
}
