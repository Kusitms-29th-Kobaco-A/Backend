package core.kobaco.application.file.service.dto;

import core.kobaco.domain.file.File;

import java.util.List;

public record DirectoryDetailResponse(
    Long directoryId,
    String directoryName,
    List<FileDetailResponse> fileList
) {
    public static DirectoryDetailResponse of(
        Long directoryId,
        String directoryName,
        List<File> fileList
    ) {
        return new DirectoryDetailResponse(
            directoryId,
            directoryName,
            fileList.stream()
                .map(file -> new FileDetailResponse(
                    file.getFileId(),
                    file.getFileName(),
                    file.getFileType()
                )).toList()
        );
    }
}
