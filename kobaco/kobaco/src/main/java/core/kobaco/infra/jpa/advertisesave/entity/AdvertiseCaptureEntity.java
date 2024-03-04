package core.kobaco.infra.jpa.advertisesave.entity;

import core.kobaco.infra.jpa.BaseEntity;
import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.file.entity.FileEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "advertise_capture")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertiseCaptureEntity extends BaseEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "capture_id")
    private Long id;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private FileEntity imageFile;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdvertisementEntity advertisement;

    public static AdvertiseCaptureEntity of(Long id, String imageUrl, FileEntity imageFile, AdvertisementEntity advertisement) {
        return new AdvertiseCaptureEntity(id, imageUrl, imageFile, advertisement);
    }
}
