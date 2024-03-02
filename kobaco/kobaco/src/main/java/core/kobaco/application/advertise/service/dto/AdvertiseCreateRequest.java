package core.kobaco.application.advertise.service.dto;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementDetail;

import java.time.LocalDate;
import java.util.List;

public record AdvertiseCreateRequest(
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
    List<String> keywordList
){
    public Advertisement toDomain(){
        return Advertisement.of(
            null,
            videoUrl,
            title,
            uploadDate,
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
