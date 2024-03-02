package core.kobaco.application.file.service.dto;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileType;

public record DirectoryCreateRequest(
    String directoryName
) {
    public File toDomain(final Long parentDirectoryId){
        return File.of(
            null,
            directoryName,
            FileType.DIRECTORY,
            parentDirectoryId,
            null
        );
    }
}
