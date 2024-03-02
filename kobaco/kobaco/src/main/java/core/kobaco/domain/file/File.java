package core.kobaco.domain.file;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class File {
    private static final String ROOT_DIRECTORY_NAME = "root";

    private Long fileId;
    private String originalPath;
    private String fileName;
    private FileType fileType;
    private Long parentFileId;
    private Long namespaceId;

    private File(Long fileId, String originalPath, String fileName, FileType fileType, Long parentFileId, Long namespaceId) {
        this.fileId = fileId;
        this.originalPath = originalPath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.parentFileId = parentFileId;
        this.namespaceId = namespaceId;
    }

    public static File of(
        Long fileId,
        String originalPath,
        String fileName,
        FileType fileType,
        Long parentFileId,
        Long namespaceId
    ) {
        return new File(fileId, originalPath, fileName, fileType, parentFileId, namespaceId);
    }

    public static File rootDirectory(Long namespaceId){
        return new File(null, null, ROOT_DIRECTORY_NAME, FileType.DIRECTORY, null, namespaceId);
    }

    public void updateDirectoryName(String directoryName){
        Assert.notNull(directoryName, "Directory name must not be null");
        this.fileName = directoryName;
    }

    public Boolean isRootDirectory(){
        return ROOT_DIRECTORY_NAME.equals(this.fileName);
    }
}
