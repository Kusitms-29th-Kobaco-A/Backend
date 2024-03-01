package core.kobaco.application.file.service.dto;


import core.kobaco.domain.file.File;

import java.util.List;

public record NamespaceDetailResponse(
    List<FileDetailResponse> files
) {
    public static NamespaceDetailResponse of(List<File> fileList) {
        return new NamespaceDetailResponse(fileList.stream()
            .map(file -> new FileDetailResponse(
                file.getFileId(),
                file.getFileName(),
                file.getFileType()
            )).toList());
    }
}
