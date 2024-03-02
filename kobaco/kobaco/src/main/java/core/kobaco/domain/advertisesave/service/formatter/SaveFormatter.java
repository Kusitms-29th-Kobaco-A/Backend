package core.kobaco.domain.advertisesave.service.formatter;

import core.kobaco.application.file.service.dto.FileDetailResponse;
import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.service.AdvertiseReader;
import core.kobaco.domain.advertisesave.AdvertiseSave;
import core.kobaco.domain.advertisesave.service.SaveReader;
import core.kobaco.domain.file.FileType;
import core.kobaco.domain.file.service.FileDetailFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveFormatter implements FileDetailFormatter {
    private static final FileType SUPPORTED_FILE_TYPE = FileType.ADVERTISE;

    private final SaveReader saveReader;
    private final AdvertiseReader advertiseReader;
    @Override
    public FileDetailResponse format(Long fileId) {
        final AdvertiseSave advertiseSave = saveReader.getAdvertiseSave(fileId);
        final Advertisement advertisement = advertiseReader.getAdvertise(advertiseSave.getAdvertiseId());
        return FileDetailResponse.of(
            fileId,
            advertisement.getTitle(),
            null,
            advertisement.getVideoUrl(),
            advertisement.getAdvertiseId(),
            FileType.ADVERTISE
        );
    }

    @Override
    public Boolean isSupported(FileType fileType) {
        return SUPPORTED_FILE_TYPE.equals(fileType);
    }
}
