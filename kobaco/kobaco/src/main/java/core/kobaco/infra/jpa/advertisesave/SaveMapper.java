package core.kobaco.infra.jpa.advertisesave;

import core.kobaco.domain.advertisesave.AdvertiseCapture;
import core.kobaco.domain.advertisesave.AdvertiseSave;
import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.advertisesave.entity.AdvertiseCaptureEntity;
import core.kobaco.infra.jpa.advertisesave.entity.AdvertiseSaveEntity;
import core.kobaco.infra.jpa.file.entity.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class SaveMapper {

    public AdvertiseSaveEntity toEntity(AdvertiseSave advertiseSave) {
        return AdvertiseSaveEntity.of(
            advertiseSave.getId(),
            AdvertisementEntity.from(advertiseSave.getAdvertiseId()),
            FileEntity.from(advertiseSave.getFileId())
        );
    }

    public AdvertiseSave toDomain(AdvertiseSaveEntity advertiseSaveEntity) {
        return AdvertiseSave.of(
            advertiseSaveEntity.getId(),
            advertiseSaveEntity.getFile().getId(),
            advertiseSaveEntity.getAdvertisement().getId()
        );
    }

    public AdvertiseCapture toDomain(AdvertiseCaptureEntity advertiseCaptureEntity) {
        return AdvertiseCapture.of(
            advertiseCaptureEntity.getId(),
            advertiseCaptureEntity.getImageUrl(),
            advertiseCaptureEntity.getImageFile().getId(),
            advertiseCaptureEntity.getAdvertisement().getId()
        );
    }

    public AdvertiseCaptureEntity toEntity(AdvertiseCapture advertiseCapture) {
        return AdvertiseCaptureEntity.of(
            advertiseCapture.getId(),
            advertiseCapture.getImageUrl(),
            FileEntity.from(advertiseCapture.getFileId()),
            AdvertisementEntity.from(advertiseCapture.getAdvertiseId())
        );
    }
}
