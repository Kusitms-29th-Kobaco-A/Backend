package core.kobaco.infra.like;

import core.kobaco.domain.like.AdvertiseLike;
import core.kobaco.infra.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public AdvertiseLikeEntity toEntity(AdvertiseLike advertiseLike) {
        return AdvertiseLikeEntity.of(
            advertiseLike.getLikeId(),
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
