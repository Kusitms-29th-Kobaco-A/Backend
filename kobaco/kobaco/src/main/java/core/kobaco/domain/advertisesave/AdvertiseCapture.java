package core.kobaco.domain.advertisesave;

import lombok.Getter;

@Getter
public class AdvertiseCapture {
    private Long captureId;
    private String imageUrl;
    private Long fileId;
    private Long advertiseId;

    public AdvertiseCapture(Long captureId, String imageUrl, Long fileId, Long advertiseId) {
        this.captureId = captureId;
        this.imageUrl = imageUrl;
        this.fileId = fileId;
        this.advertiseId = advertiseId;
    }

    public static AdvertiseCapture of(String imageUrl, Long fileId, Long advertiseId) {
        return new AdvertiseCapture(null, imageUrl, fileId, advertiseId);
    }

    public static AdvertiseCapture of(Long captureId, String imageUrl, Long fileId, Long advertiseId) {
        return new AdvertiseCapture(captureId, imageUrl, fileId, advertiseId);
    }
}
