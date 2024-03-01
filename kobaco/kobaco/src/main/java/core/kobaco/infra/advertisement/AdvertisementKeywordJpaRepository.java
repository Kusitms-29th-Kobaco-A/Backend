package core.kobaco.infra.advertisement;

import core.kobaco.domain.advertise.AdvertisementKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementKeywordJpaRepository extends JpaRepository<AdvertisementKeywordEntity, Long> {
    List<AdvertisementKeywordEntity> findAllByAdvertisementId(Long advertiseId);
}
