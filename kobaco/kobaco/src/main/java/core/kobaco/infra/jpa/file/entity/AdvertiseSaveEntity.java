package core.kobaco.infra.jpa.file.entity;

import core.kobaco.infra.jpa.BaseEntity;
import core.kobaco.infra.advertisement.entity.AdvertisementEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "save")
@Getter
@NoArgsConstructor
public class AdvertiseSaveEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private AdvertisementEntity advertisement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    private FileEntity file;

    private AdvertiseSaveEntity(AdvertisementEntity advertisement, FileEntity file) {
        this.advertisement = advertisement;
        this.file = file;
    }

    public static AdvertiseSaveEntity of(AdvertisementEntity advertisement, FileEntity file){
        return new AdvertiseSaveEntity(advertisement, file);
    }
}
