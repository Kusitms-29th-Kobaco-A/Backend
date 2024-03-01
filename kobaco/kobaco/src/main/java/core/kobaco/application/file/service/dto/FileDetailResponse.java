package core.kobaco.application.file.service.dto;

import core.kobaco.domain.file.FileType;

public record FileDetailResponse(
    Long fileId,
    String fileName,
    FileType fileType
) {
}
