package core.kobaco.infra.jpa.advertisement.repository;

import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementJpaRepository extends JpaRepository<AdvertisementEntity, Long> {
}
