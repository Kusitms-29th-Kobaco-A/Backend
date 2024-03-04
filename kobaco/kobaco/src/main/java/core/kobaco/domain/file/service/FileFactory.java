package core.kobaco.domain.file.service;

import core.kobaco.domain.advertise.service.AdvertiseReader;
import core.kobaco.domain.file.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileFactory {
    private final NamespaceRepository namespaceRepository;
    private final FileRepository fileRepository;
    private final FileReader fileReader;
    private final AdvertiseReader advertiseReader;

    private Namespace upsert(final Long userId) {
        return namespaceRepository.findByUserId(userId)
            .orElseGet(() -> namespaceRepository.save(Namespace.from(userId)));
    }

    public File createRootDirectory(final Long userId) {
        Namespace namespace = upsert(userId);
        return fileRepository.findRootDirectoryByNamespaceId(namespace.getId())
            .orElseGet(() -> {
                    final File rootDirectory = fileRepository.save(File.rootDirectory(namespace.getId()));
//                    fileRepository.save(File.captureDirectory(rootDirectory.getFileId()));
                    fileRepository.save(File.basicDirectory(rootDirectory.getId(), namespace.getId()));
                    return rootDirectory;
                }
            );
    }

    public File createAdvertiseCaptureDirectory(final Long advertiseId, final Long userId) {
        final String advertiseTitle = advertiseReader.getAdvertise(advertiseId).getTitle();
        return fileRepository.findByFileName(advertiseTitle)
            .orElseGet(() -> {
                final File captureDirectory = fileReader.getCaptureDirectoryByUserId(userId);
                return fileRepository.save(File.of(advertiseTitle, FileType.DIRECTORY, captureDirectory.getId()));
            });
    }

    public File createBasicDirectory(final Long userId) {
        final File rootDirectory = createRootDirectory(userId);
        return fileRepository.findBasicDirectoryByUserId(userId)
            .orElseGet(() -> fileRepository.save(File.basicDirectory(rootDirectory.getId(), rootDirectory.getNamespaceId())));
    }


}
