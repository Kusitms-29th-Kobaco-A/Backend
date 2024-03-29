package core.kobaco.application.advertise.service.dto;

import core.kobaco.domain.advertise.Advertisement;

import java.time.LocalDate;
import java.util.List;

public record AdvertiseDetailResponse(
    Long advertiseId,
    String videoUrl,
    String title,
    LocalDate uploadDate,
    String copy,
    String copyDetail,
    List<String> peopleList,
    List<String> objectList,
    String owner,
    String ownerCompany,
    String makerCompany,
    List<String> keywordList,
    Long viewCount
) {

    public static AdvertiseDetailResponse of(Advertisement advertisement, List<String> keywordList){
        return new AdvertiseDetailResponse(
            advertisement.getId(),
            advertisement.getVideoUrl(),
            advertisement.getTitle(),
            advertisement.getUploadDate(),
            advertisement.getCopy(),
            advertisement.getCopyDetail(),
            advertisement.getAdvertisementDetail().getPeopleList(),
            advertisement.getAdvertisementDetail().getObjectList(),
            advertisement.getAdvertisementDetail().getOwner(),
            advertisement.getAdvertisementDetail().getOwnerCompany(),
            advertisement.getAdvertisementDetail().getMakerCompany(),
            keywordList,
            advertisement.getViewCount()
        );
    }
}
