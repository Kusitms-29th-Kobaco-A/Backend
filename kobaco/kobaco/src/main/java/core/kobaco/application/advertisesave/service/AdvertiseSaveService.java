package core.kobaco.application.advertisesave.service;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.service.AdvertiseReader;
import core.kobaco.domain.advertisesave.service.CaptureAppender;
import core.kobaco.domain.advertisesave.service.SaveAppender;
import core.kobaco.domain.file.File;
import core.kobaco.domain.file.service.FileFactory;
import core.kobaco.domain.file.service.FileReader;
import core.kobaco.domain.user.User;
import core.kobaco.domain.user.UserUtils;
import core.kobaco.global.image.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiseSaveService {
    private final SaveAppender saveAppender;
    private final FileReader fileReader;
    private final FileFactory fileFactory;
    private final AdvertiseReader advertiseReader;
    private final ImageUploader imageUploader;
    private final CaptureAppender captureAppender;
    private final UserUtils userUtils;

    @Transactional
    public void saveAdvertise(Long directoryId, Long advertiseId) {
        final File directory = fileReader.getFile(directoryId);
        final Advertisement advertisement = advertiseReader.getAdvertise(advertiseId);
        saveAppender.append(advertisement, directory);
    }

    @Transactional
    public void captureAdvertise(MultipartFile imageFile, Long advertiseId) {
        final User user = userUtils.getRequestUser();
        final String imageUrl = imageUploader.uploadImage(imageFile);
//        final File captureDirectory = fileFactory.createAdvertiseCaptureDirectory(advertiseId, user.getId());
        captureAppender.append(fileFactory.createBasicDirectory(user.getId()), advertiseId, imageUrl);
    }
}
