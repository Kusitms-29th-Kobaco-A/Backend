package core.kobaco.domain.advertise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Advertisement {
    private Long advertiseId;
    private String videoUrl;
    private String title;
    private String description;
    private LocalDate uploadDate;
    private String copy;
    private String copyDetail;
    private AdvertisementDetail advertisementDetail;

    public static Advertisement of(Long advertiseId,
                                   String videoUrl,
                                   String title,
                                   String description,
                                   LocalDate uploadDate,
                                   String copy,
                                   String copyDetail,
                                   AdvertisementDetail advertisementDetail) {
        return new Advertisement(advertiseId, videoUrl, title, description, uploadDate,copy, copyDetail, advertisementDetail);
    }
}
