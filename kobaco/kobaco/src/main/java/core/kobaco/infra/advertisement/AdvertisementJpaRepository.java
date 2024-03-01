package core.kobaco.infra.advertisement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementJpaRepository extends JpaRepository<AdvertisementEntity, Long> {
}
