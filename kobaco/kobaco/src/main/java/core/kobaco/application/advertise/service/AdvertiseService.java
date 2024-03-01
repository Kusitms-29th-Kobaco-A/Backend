package core.kobaco.application.advertise.service;

import core.kobaco.application.advertise.service.dto.AdvertiseCreateRequest;
import core.kobaco.application.advertise.service.dto.AdvertiseDetailResponse;
import core.kobaco.domain.advertise.service.AdvertiseAppender;
import core.kobaco.domain.advertise.service.AdvertiseReader;
import core.kobaco.domain.keyword.service.KeywordFactory;
import core.kobaco.domain.keyword.service.KeywordReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiseService {
    private final AdvertiseReader advertiseReader;
    private final AdvertiseAppender advertiseAppender;
    private final KeywordReader keywordReader;
    private final KeywordFactory keywordFactory;

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

    public void likeAdvertise(final Long advertiseId) {

    }

    public void saveAdvertise(final Long advertiseId) {

    }

    public void captureAdvertise(final Long advertiseId, MultipartFile multipartFile) {

    }


}
