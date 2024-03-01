package core.kobaco.infra.advertisement.repository;

import core.kobaco.infra.advertisement.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementJpaRepository extends JpaRepository<AdvertisementEntity, Long> {
}
