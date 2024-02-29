package core.kobaco.infra.file;

import core.kobaco.infra.advertisement.AdvertisementEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "save")
@Getter
@NoArgsConstructor
public class SaveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

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
