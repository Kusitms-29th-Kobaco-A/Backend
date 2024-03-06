package core.kobaco.application.file.service.dto;

import core.kobaco.domain.file.File;

import java.util.List;
import java.util.function.Function;

public record DirectoryDetailResponse(
    Long directoryId,
    String directoryName,
    List<FileDetailResponse> fileList
) {

    public static DirectoryDetailResponse of(
        Long directoryId,
        String directoryName,
        List<File> fileList,
        Function<File, FileDetailResponse> formatter
    ) {
        return new DirectoryDetailResponse(
            directoryId,
            directoryName,
            fileList.stream()
                .map(formatter)
                .toList()
        );
    }
}
