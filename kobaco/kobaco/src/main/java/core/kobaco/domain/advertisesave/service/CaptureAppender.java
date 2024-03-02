package core.kobaco.domain.advertisesave.service;

import core.kobaco.domain.advertisesave.AdvertiseCapture;
import core.kobaco.domain.advertisesave.AdvertiseCaptureRepository;
import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import core.kobaco.domain.file.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaptureAppender {
    private final AdvertiseCaptureRepository advertiseCaptureRepository;
    private final FileRepository fileRepository;

    public void append(Long captureDirectoryId, Long advertiseId,String imageUrl) {
        final File imageFile = fileRepository.save(File.of(FileType.IMAGE, captureDirectoryId));
        advertiseCaptureRepository.save(AdvertiseCapture.of(imageUrl, imageFile.getFileId(), advertiseId));
    }
}
