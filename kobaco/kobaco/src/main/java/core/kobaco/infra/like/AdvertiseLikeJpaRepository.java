package core.kobaco.infra.like;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertiseLikeJpaRepository extends JpaRepository<AdvertiseLikeEntity, Long> {

    Long countByAdvertisementId(Long advertiseId);

    Boolean existsByAdvertisementIdAndUserId(Long advertiseId, Long userId);

    Optional<AdvertiseLikeEntity> findByAdvertisementIdAndUserId(Long advertiseId, Long userId);
}
