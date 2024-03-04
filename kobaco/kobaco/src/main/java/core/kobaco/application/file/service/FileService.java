package core.kobaco.application.file.service;

import core.kobaco.application.file.service.dto.DirectoryCreateRequest;
import core.kobaco.application.file.service.dto.DirectoryDetailResponse;
import core.kobaco.application.file.service.dto.DirectoryUpdateRequest;
import core.kobaco.application.file.service.dto.FileMoveRequest;
import core.kobaco.domain.file.File;
import core.kobaco.domain.file.Namespace;
import core.kobaco.domain.file.service.*;
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
    private final FileFormatter fileFormatter;
    private final FileModifier fileModifier;

    @Transactional
    public DirectoryDetailResponse getFiles(Long directoryId) {
        final File directory = Objects.isNull(directoryId)
            ? fileFactory.createRootDirectory(userUtils.getRequestUserId())
            : fileReader.getFile(directoryId);
        return DirectoryDetailResponse.of(
            directory.getId(),
            directory.getFileName(),
            fileReader.getDirectoryFiles(directory.getId()),
            fileFormatter::format
        );
    }

    @Transactional
    public void createDirectory(Long parentDirectoryId, DirectoryCreateRequest request) {
        final Namespace namespace = fileReader.getNamespaceByUserId(userUtils.getRequestUserId());
        fileAppender.append(request.toDomain(parentDirectoryId, namespace.getId()));
    }

    @Transactional
    public void updateDirectoryName(Long directoryId, DirectoryUpdateRequest request) {
        fileModifier.updateDirectory(directoryId, request.directoryName());
    }

    @Transactional
    public void moveDirectory(Long directoryId, FileMoveRequest request) {
        fileModifier.moveDirectory(directoryId, request.targetDirectoryId());
    }

    @Transactional
    public void deleteDirectory(Long directoryId) {
        fileModifier.deleteDirectory(directoryId);
    }
}
