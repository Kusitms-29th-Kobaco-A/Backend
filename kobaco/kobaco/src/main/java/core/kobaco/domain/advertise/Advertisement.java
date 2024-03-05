package core.kobaco.domain.advertise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Advertisement {
    private Long id;
    private String videoUrl;
    private Time videoTime;
    private String title;
    private LocalDate uploadDate;
    private String copy;
    private String copyDetail;
    private AdvertisementDetail advertisementDetail;
    private Long viewCount;

    public static Advertisement of(Long advertiseId,
                                   String videoUrl,
                                   Time videoTime,
                                   String title,
                                   LocalDate uploadDate,
                                   String copy,
                                   String copyDetail,
                                   AdvertisementDetail advertisementDetail,
                                   Long viewCount) {
        return new Advertisement(advertiseId, videoUrl, videoTime, title, uploadDate, copy, copyDetail, advertisementDetail, viewCount);
    }
}
