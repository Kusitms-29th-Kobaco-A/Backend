package core.kobaco.application.file.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import core.kobaco.domain.file.FileType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FileDetailResponse(
    Long fileId,
    String fileName,
    String imageUrl,
    String videoUrl,
    Long domainId,
    FileType fileType
) {

    public static FileDetailResponse of(
        Long fileId,
        String fileName,
        String imageUrl,
        String videoUrl,
        Long domainId,
        FileType fileType
    ) {
        return new FileDetailResponse(
            fileId,
            fileName,
            imageUrl,
            videoUrl,
            domainId,
            fileType
        );
    }
}
