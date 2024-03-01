package core.kobaco.infra.advertisement.repository;

import core.kobaco.infra.advertisement.entity.AdvertisementKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementKeywordJpaRepository extends JpaRepository<AdvertisementKeywordEntity, Long> {
    List<AdvertisementKeywordEntity> findAllByAdvertisementId(Long advertiseId);
}
