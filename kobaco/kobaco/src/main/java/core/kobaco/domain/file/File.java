package core.kobaco.domain.file;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class File {
    private static final String ROOT_DIRECTORY_NAME = "root";
    private static final String CAPTURE_DIRECTORY_NAME = "capture";
    private static final String BASIC_DIRECTORY_NAME = "기본 폴더";

    private Long fileId;
    private String fileName;
    private FileType fileType;
    private Long parentFileId;
    private Long namespaceId;

    private File(Long fileId, String fileName, FileType fileType, Long parentFileId, Long namespaceId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.parentFileId = parentFileId;
        this.namespaceId = namespaceId;
    }

    public static File of(
        Long fileId,
        String fileName,
        FileType fileType,
        Long parentFileId,
        Long namespaceId
    ) {
        return new File(fileId, fileName, fileType, parentFileId, namespaceId);
    }

    public static File rootDirectory(Long namespaceId) {
        return new File(null, ROOT_DIRECTORY_NAME, FileType.DIRECTORY, null, namespaceId);
    }

    public static File captureDirectory(Long parentFileId) {
        return new File(null, CAPTURE_DIRECTORY_NAME, FileType.CAPTURE_DIRECTORY, parentFileId, null);
    }

public static File basicDirectory(Long parentFileId) {
        return new File(null, BASIC_DIRECTORY_NAME, FileType.BASIC_DIRECTORY, parentFileId, null);
    }

    public static File of(String fileName, FileType fileType, Long parentFileId) {
        return new File(null, fileName, fileType, parentFileId, null);
    }

    public static File of(FileType fileType, Long parentFileId) {
        return new File(null, null, fileType, parentFileId, null);
    }

    public void updateDirectoryName(String directoryName) {
        Assert.notNull(directoryName, "Directory name must not be null");
        this.fileName = directoryName;
    }

    public Boolean isRootDirectory() {
        return ROOT_DIRECTORY_NAME.equals(this.fileName);
    }
}
