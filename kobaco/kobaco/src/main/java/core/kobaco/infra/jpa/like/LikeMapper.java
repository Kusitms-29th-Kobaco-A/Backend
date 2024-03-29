package core.kobaco.infra.jpa.like;

import core.kobaco.domain.like.AdvertiseLike;
import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public AdvertiseLikeEntity toEntity(AdvertiseLike advertiseLike) {
        return AdvertiseLikeEntity.of(
            advertiseLike.getId(),
            UserEntity.from(advertiseLike.getUserId()),
            AdvertisementEntity.from(advertiseLike.getAdvertiseId())
        );
    }

    public AdvertiseLike toDomain(AdvertiseLikeEntity advertiseLikeEntity) {
        return AdvertiseLike.of(
            advertiseLikeEntity.getId(),
            advertiseLikeEntity.getUser().getId(),
            advertiseLikeEntity.getAdvertisement().getId()
        );
    }
}
