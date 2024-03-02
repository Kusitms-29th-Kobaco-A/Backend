package core.kobaco.infra.jpa.advertisesave.repository;

import core.kobaco.infra.jpa.advertisesave.entity.AdvertiseCaptureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertiseCaptureJpaRepository extends JpaRepository<AdvertiseCaptureEntity, Long> {
    Optional<AdvertiseCaptureEntity> findByImageFileId(Long fileId);
}
