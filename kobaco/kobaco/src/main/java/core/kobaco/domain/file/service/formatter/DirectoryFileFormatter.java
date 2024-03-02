package core.kobaco.domain.file.service.formatter;

import core.kobaco.application.file.service.dto.FileDetailResponse;
import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileType;
import core.kobaco.domain.file.service.FileDetailFormatter;
import core.kobaco.domain.file.service.FileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class DirectoryFileFormatter implements FileDetailFormatter {
    private static final EnumSet<FileType> SUPPORTED_FILE_TYPES =
        EnumSet.of(FileType.DIRECTORY,FileType.BASIC_DIRECTORY, FileType.CAPTURE_DIRECTORY);

    private final FileReader fileReader;
    @Override
    public FileDetailResponse format(Long fileId) {
        final File file = fileReader.getFile(fileId);
        return FileDetailResponse.of(
            fileId,
            file.getFileName(),
            null,
            null,
            null,
            file.getFileType()
        );
    }

    @Override
    public Boolean isSupported(FileType fileType) {
        return SUPPORTED_FILE_TYPES.contains(fileType);
    }
}
