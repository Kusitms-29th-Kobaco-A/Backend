package core.kobaco.domain.advertise;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Advertisement {
    private Long advertiseId;
    private String videoUrl;
    private String title;
    private String description;
    private String copy;
    private String copyDetail;
    private AdvertisementDetail advertisementDetail;

    public static Advertisement of(Long advertiseId,
                                   String videoUrl,
                                   String title,
                                   String description,
                                   String copy,
                                   String copyDetail,
                                   AdvertisementDetail advertisementDetail) {
        return new Advertisement(advertiseId, videoUrl, title, description, copy, copyDetail, advertisementDetail);
    }
}
