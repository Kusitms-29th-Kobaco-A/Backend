package core.kobaco.infra.file.entity;

import core.kobaco.infra.BaseEntity;
import core.kobaco.infra.advertisement.entity.AdvertisementEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "save")
@Getter
@NoArgsConstructor
public class SaveEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saveId;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private AdvertisementEntity advertisement;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private FileEntity file;

    private SaveEntity(AdvertisementEntity advertisement, FileEntity file) {
        this.advertisement = advertisement;
        this.file = file;
    }

    public static SaveEntity of(AdvertisementEntity advertisement, FileEntity file){
        return new  SaveEntity(advertisement, file);
    }
}
