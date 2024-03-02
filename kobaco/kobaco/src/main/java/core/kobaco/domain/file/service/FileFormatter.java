package core.kobaco.domain.file.service;

import core.kobaco.application.file.service.dto.FileDetailResponse;
import core.kobaco.domain.file.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileFormatter {
    private final List<FileDetailFormatter> fileDetailFormatters;


    public FileDetailResponse format(File file) {
        return fileDetailFormatters.stream()
            .filter(formatter -> formatter.isSupported(file.getFileType()))
            .findFirst()
            .map(formatter -> formatter.format(file.getFileId()))
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 파일 형식입니다."));
    }
}
