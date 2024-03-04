package core.kobaco.infra.jpa.advertisement.repository;

import core.kobaco.infra.jpa.advertisement.entity.AdvertisementTrendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementTrendJpaRepository extends JpaRepository<AdvertisementTrendEntity, Long> {
}
