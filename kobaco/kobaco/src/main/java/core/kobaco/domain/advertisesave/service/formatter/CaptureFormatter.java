package core.kobaco.domain.advertisesave.service.formatter;

import core.kobaco.application.file.service.dto.FileDetailResponse;
import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.service.AdvertiseReader;
import core.kobaco.domain.advertisesave.AdvertiseCapture;
import core.kobaco.domain.advertisesave.service.CaptureReader;
import core.kobaco.domain.file.FileType;
import core.kobaco.domain.file.service.FileDetailFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaptureFormatter implements FileDetailFormatter {
    private static final FileType SUPPORTED_FILE_TYPE = FileType.IMAGE;

    private final CaptureReader captureReader;
    private final AdvertiseReader advertiseReader;

    @Override
    public FileDetailResponse format(Long fileId) {
        final AdvertiseCapture advertiseCapture = captureReader.getAdvertiseCapture(fileId);
        final Advertisement advertisement = advertiseReader.getAdvertise(advertiseCapture.getAdvertiseId());
        return FileDetailResponse.of(
            fileId,
            advertisement.getTitle(),
            advertiseCapture.getImageUrl(),
            null,
            advertisement.getId(),
            FileType.IMAGE
        );
    }

    @Override
    public Boolean isSupported(FileType fileType) {
        return SUPPORTED_FILE_TYPE.equals(fileType);
    }
}
