package core.kobaco.domain.advertise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Advertisement {
    private Long advertiseId;
    private String videoUrl;
    private String title;
    private LocalDate uploadDate;
    private String copy;
    private String copyDetail;
    private AdvertisementDetail advertisementDetail;

    public static Advertisement of(Long advertiseId,
                                   String videoUrl,
                                   String title,
                                   LocalDate uploadDate,
                                   String copy,
                                   String copyDetail,
                                   AdvertisementDetail advertisementDetail) {
        return new Advertisement(advertiseId, videoUrl, title, uploadDate,copy, copyDetail, advertisementDetail);
    }
}
