package core.kobaco.application.advertise.service.dto;

import java.sql.Time;
import java.util.List;

public record TrendAdvertiseSimpleResponse(
    Long advertiseId,
    String videoUrl,
    String title,
    Time videoTime,
    List<String> keywordList,
    String trendTitle
) {
    public static TrendAdvertiseSimpleResponse of(AdvertiseSimpleResponse advertiseSimpleResponse, String trendTitle) {
        return new TrendAdvertiseSimpleResponse(
            advertiseSimpleResponse.advertiseId(),
            advertiseSimpleResponse.videoUrl(),
            advertiseSimpleResponse.title(),
            advertiseSimpleResponse.videoTime(),
            advertiseSimpleResponse.keywordList(),
            trendTitle
        );
    }
}
