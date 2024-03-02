package core.kobaco.infra.advertisement;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementDetail;
import core.kobaco.domain.advertise.AdvertisementKeyword;
import core.kobaco.infra.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.advertisement.entity.AdvertisementKeywordEntity;
import core.kobaco.infra.jpa.keyword.KeywordEntity;
import org.springframework.stereotype.Component;

@Component
public class AdvertiseMapper {

    public AdvertisementEntity toEntity(Advertisement advertisement) {
        return AdvertisementEntity.of(
            advertisement.getAdvertiseId(),
            advertisement.getVideoUrl(),
            advertisement.getTitle(),
            advertisement.getDescription(),
            advertisement.getUploadDate(),
            advertisement.getCopy(),
            advertisement.getCopyDetail(),
            advertisement.getAdvertisementDetail().getPeopleList(),
            advertisement.getAdvertisementDetail().getObjectList(),
            advertisement.getAdvertisementDetail().getOwner(),
            advertisement.getAdvertisementDetail().getOwnerCompany(),
            advertisement.getAdvertisementDetail().getMakerCompany()
        );
    }

    public Advertisement toDomain(AdvertisementEntity advertisementEntity) {
        return Advertisement.of(
            advertisementEntity.getId(),
            advertisementEntity.getVideoUrl(),
            advertisementEntity.getTitle(),
            advertisementEntity.getDescription(),
            advertisementEntity.getUploadDate(),
            advertisementEntity.getCopy(),
            advertisementEntity.getCopyDetail(),
            AdvertisementDetail.of(
                advertisementEntity.getPeopleList(),
                advertisementEntity.getObjectList(),
                advertisementEntity.getOwner(),
                advertisementEntity.getOwnerCompany(),
                advertisementEntity.getMakerCompany()
            )
        );
    }

    public AdvertisementKeyword toDomain(AdvertisementKeywordEntity advertisementKeywordEntity) {
        return AdvertisementKeyword.of(
            advertisementKeywordEntity.getId(),
            advertisementKeywordEntity.getAdvertisement().getId(),
            advertisementKeywordEntity.getKeyword().getKeywordId()
        );
    }

    public AdvertisementKeywordEntity toEntity(AdvertisementKeyword advertisementKeyword) {
        return AdvertisementKeywordEntity.of(
            advertisementKeyword.getAdvertiseKeywordId(),
            AdvertisementEntity.from(advertisementKeyword.getAdvertiseId()),
            KeywordEntity.from(advertisementKeyword.getKeywordId())
        );
    }
}
