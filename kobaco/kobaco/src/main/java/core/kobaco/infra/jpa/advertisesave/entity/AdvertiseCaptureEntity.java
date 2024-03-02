package core.kobaco.infra.jpa.advertisesave.entity;

import core.kobaco.infra.jpa.BaseEntity;
import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.file.entity.FileEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "advertise_capture")
public class AdvertiseCaptureEntity extends BaseEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "capture_id")
    private Long id;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private FileEntity imageFile;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdvertisementEntity advertisement;

    private AdvertiseCaptureEntity(Long id, String imageUrl, FileEntity imageFile, AdvertisementEntity advertisement) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageFile = imageFile;
        this.advertisement = advertisement;
    }

    public static AdvertiseCaptureEntity of(Long id, String imageUrl, FileEntity imageFile, AdvertisementEntity advertisement) {
        return new AdvertiseCaptureEntity(id, imageUrl, imageFile, advertisement);
    }
}
