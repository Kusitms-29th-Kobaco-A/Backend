package core.kobaco.infra.jpa.file.entity;

import core.kobaco.domain.file.FileType;
import core.kobaco.infra.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "file")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SQLDelete(sql = "UPDATE file SET is_deleted = true WHERE file_id = ?")
@SQLRestriction("is_deleted = false")
public class FileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String fileName;
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_file_id")
    private FileEntity parentFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namespace_id")
    private NamespaceEntity namespace;

    private Boolean isDeleted;

    private FileEntity(Long id, String fileName, FileType fileType, FileEntity parentFile, NamespaceEntity namespace) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.parentFile = parentFile;
        this.namespace = namespace;
        this.isDeleted = false;
    }

    public static FileEntity of(
        Long fileId,
        String fileName,
        FileType fileType,
        FileEntity parentFile,
        NamespaceEntity namespace
    ) {
        return new FileEntity(fileId, fileName, fileType, parentFile, namespace);
    }

    public static FileEntity from(Long fileId) {
        return new FileEntity(fileId, null, null, null, null);
    }


}
