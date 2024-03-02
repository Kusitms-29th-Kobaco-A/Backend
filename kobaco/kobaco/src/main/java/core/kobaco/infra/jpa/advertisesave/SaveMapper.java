package core.kobaco.infra.jpa.advertisesave;

import core.kobaco.domain.advertisesave.AdvertiseSave;
import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.file.entity.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class SaveMapper {

    public AdvertiseSaveEntity toEntity(AdvertiseSave advertiseSave) {
        return AdvertiseSaveEntity.of(
            advertiseSave.getSaveId(),
            AdvertisementEntity.from(advertiseSave.getAdvertiseId()),
            FileEntity.from(advertiseSave.getFileId())
        );
    }

    public AdvertiseSave toDomain(AdvertiseSaveEntity advertiseSaveEntity) {
        return AdvertiseSave.of(
            advertiseSaveEntity.getId(),
            advertiseSaveEntity.getAdvertisement().getId(),
            advertiseSaveEntity.getFile().getId()
        );
    }
}
