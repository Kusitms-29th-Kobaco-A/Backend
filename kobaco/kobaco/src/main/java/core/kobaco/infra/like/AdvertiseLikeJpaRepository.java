package core.kobaco.infra.like;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiseLikeJpaRepository extends JpaRepository<AdvertiseLikeEntity, Long> {

    Long countByAdvertisementId(Long advertiseId);

    Boolean existsByAdvertisementIdAndUserId(Long advertiseId, Long userId);
}
