package core.kobaco.application.advertise.service.dto;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementDetail;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AdvertiseCreateRequest(
    String videoUrl,
    @Schema(description = "00:00:00 형식", example = "00:00:00", type = "string")
    LocalTime videoTime,
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
            Time.valueOf(videoTime),
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
