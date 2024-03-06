package core.kobaco.application.advertise.service.dto;

import core.kobaco.domain.advertise.Advertisement;

import java.sql.Time;
import java.util.List;

public record AdvertiseSimpleResponse (
    Long advertiseId,
    String videoUrl,
    String title,
    Time videoTime,
    List<String> keywordList
){
    public static AdvertiseSimpleResponse of(Advertisement advertisement, List<String> keywordList){
        return new AdvertiseSimpleResponse(
            advertisement.getId(),
            advertisement.getVideoUrl(),
            advertisement.getTitle(),
            advertisement.getVideoTime(),
            keywordList
        );
    }
}
