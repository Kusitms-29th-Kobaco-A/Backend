package core.kobaco.application.file.service;

import core.kobaco.application.file.service.dto.DirectoryCreateRequest;
import core.kobaco.application.file.service.dto.DirectoryDetailResponse;
import core.kobaco.application.file.service.dto.DirectoryUpdateRequest;
import core.kobaco.domain.file.File;
import core.kobaco.domain.file.service.FileAppender;
import core.kobaco.domain.file.service.FileFactory;
import core.kobaco.domain.file.service.FileModifier;
import core.kobaco.domain.file.service.FileReader;
import core.kobaco.domain.user.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {
    private final UserUtils userUtils;
    private final FileReader fileReader;
    private final FileFactory fileFactory;
    private final FileAppender fileAppender;
    private final FileModifier fileModifier;

    @Transactional
    public DirectoryDetailResponse getFiles(Long directoryId) {
        final File directory = Objects.isNull(directoryId)
            ? fileFactory.createRootDirectory(userUtils.getRequestUserId())
            : fileReader.getDirectory(directoryId);
        return DirectoryDetailResponse.of(
            directory.getFileId(),
            directory.getFileName(),
            fileReader.getDirectoryFiles(directory.getFileId())
        );
    }

    @Transactional
    public void createDirectory(Long parentDirectoryId, DirectoryCreateRequest request) {
        fileAppender.append(request.toDomain(parentDirectoryId));
    }

    @Transactional
    public void updateDirectoryName(Long directoryId, DirectoryUpdateRequest request) {
        fileModifier.updateDirectory(directoryId, request.directoryName());
    }
}
