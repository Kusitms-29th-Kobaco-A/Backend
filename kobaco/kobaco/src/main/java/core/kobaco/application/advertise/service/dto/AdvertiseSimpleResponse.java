package core.kobaco.application.advertise.service.dto;

import java.sql.Time;
import java.util.List;

public record AdvertiseSimpleResponse (
    Long advertiseId,
    String videoUrl,
    String title,
    Time videoTime,
    List<String> keywordList
){
}
