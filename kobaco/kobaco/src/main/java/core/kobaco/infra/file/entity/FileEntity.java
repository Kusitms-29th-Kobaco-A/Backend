package core.kobaco.infra.file.entity;

import core.kobaco.domain.file.FileType;
import core.kobaco.infra.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file")
@Getter
@NoArgsConstructor
public class FileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    private String originalPath;
    private String fileName;
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @ManyToOne
    @JoinColumn(name = "parent_file_id")
    private FileEntity parentFile;

    @ManyToOne
    @JoinColumn(name = "namespace_id", nullable = false)
    private NamespaceEntity namespace;

    private FileEntity(Long fileId, String originalPath, String fileName, FileType fileType, FileEntity parentFile, NamespaceEntity namespace) {
        this.fileId = fileId;
        this.originalPath = originalPath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.parentFile = parentFile;
        this.namespace = namespace;
    }

    public static FileEntity of(
        Long fileId,
        String originalPath,
        String fileName,
        FileType fileType,
        FileEntity parentFile,
        NamespaceEntity namespace
    ) {
        return new FileEntity(fileId, originalPath, fileName, fileType, parentFile, namespace);
    }


}
