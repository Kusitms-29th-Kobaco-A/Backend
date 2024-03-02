package core.kobaco.domain.file.service;

import core.kobaco.application.file.service.dto.FileDetailResponse;
import core.kobaco.domain.file.FileType;

public interface FileDetailFormatter {
    FileDetailResponse format(Long fileId);

    Boolean isSupported(FileType fileType);
}
