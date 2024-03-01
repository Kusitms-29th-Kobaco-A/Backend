package core.kobaco.infra.namespace;

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
    private String relativePath;
    private boolean isDirectory;

    @ManyToOne
    @JoinColumn(name = "parent_file_id")
    private FileEntity parentFile;

    @ManyToOne
    @JoinColumn(name = "namespace_id", nullable = false)
    private NamespaceEntity namespace;

    private FileEntity(String originalPath, String relativePath, boolean isDirectory, FileEntity parentFile, NamespaceEntity namespace) {
        this.originalPath = originalPath;
        this.relativePath = relativePath;
        this.isDirectory = isDirectory;
        this.parentFile = parentFile;
        this.namespace = namespace;
    }

    public static FileEntity of (String originalPath, String relativePath, boolean isDirectory, FileEntity parentFile, NamespaceEntity namespace){
        return new FileEntity(originalPath, relativePath, isDirectory, parentFile, namespace);
    }
}
