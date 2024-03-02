package core.kobaco.domain.advertisesave;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertiseSave {
    private Long saveId;
    private Long fileId;
    private Long advertiseId;

    public static AdvertiseSave of(Long saveId, Long fileId, Long advertiseId){
        return new AdvertiseSave(saveId, fileId, advertiseId);
    }

    public static AdvertiseSave of(Long fileId, Long advertiseId){
        return new AdvertiseSave(null, fileId, advertiseId);
    }
}
