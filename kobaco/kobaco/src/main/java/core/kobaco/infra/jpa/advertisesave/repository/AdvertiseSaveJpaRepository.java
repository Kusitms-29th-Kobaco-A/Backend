package core.kobaco.infra.jpa.advertisesave.repository;

import core.kobaco.infra.jpa.advertisesave.entity.AdvertiseSaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertiseSaveJpaRepository extends JpaRepository<AdvertiseSaveEntity, Long> {
    Optional<AdvertiseSaveEntity> findByFileId(Long fileId);

}
