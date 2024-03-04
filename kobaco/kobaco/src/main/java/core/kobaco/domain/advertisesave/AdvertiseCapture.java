package core.kobaco.domain.advertisesave;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertiseCapture {
    private Long id;
    private String imageUrl;
    private Long fileId;
    private Long advertiseId;

    public static AdvertiseCapture of(String imageUrl, Long fileId, Long advertiseId) {
        return new AdvertiseCapture(null, imageUrl, fileId, advertiseId);
    }

    public static AdvertiseCapture of(Long captureId, String imageUrl, Long fileId, Long advertiseId) {
        return new AdvertiseCapture(captureId, imageUrl, fileId, advertiseId);
    }
}
