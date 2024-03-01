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
    private Long id;

    private String originalPath;
    private String fileName;
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_file_id")
    private FileEntity parentFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namespace_id")
    private NamespaceEntity namespace;

    private FileEntity(Long id, String originalPath, String fileName, FileType fileType, FileEntity parentFile, NamespaceEntity namespace) {
        this.id = id;
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

    public static FileEntity from(Long fileId) {
        return new FileEntity(fileId, null, null, null, null, null);
    }


}
