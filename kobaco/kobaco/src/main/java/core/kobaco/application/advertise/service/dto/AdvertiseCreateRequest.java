package core.kobaco.application.advertise.service.dto;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementDetail;

import java.util.List;

public record AdvertiseCreateRequest(
    String videoUrl,
    String title,
    String description,
    String copy,
    String copyDetail,
    List<String> peopleList,
    List<String> objectList,
    String owner,
    String ownerCompany,
    String makerCompany,
    List<String> keywordList
){
    public Advertisement toDomain(){
        return Advertisement.of(
            null,
            videoUrl,
            title,
            description,
            copy,
            copyDetail,
            AdvertisementDetail.of(
                peopleList,
                objectList,
                owner,
                ownerCompany,
                makerCompany
            )
        );
    }
}
