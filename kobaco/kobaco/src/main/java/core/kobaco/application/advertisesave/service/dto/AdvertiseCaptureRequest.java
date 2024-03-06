package core.kobaco.application.advertisesave.service.dto;

import org.springframework.web.multipart.MultipartFile;

public record AdvertiseCaptureRequest(
    Long advertiseId,
    MultipartFile captureFile
) {
}
