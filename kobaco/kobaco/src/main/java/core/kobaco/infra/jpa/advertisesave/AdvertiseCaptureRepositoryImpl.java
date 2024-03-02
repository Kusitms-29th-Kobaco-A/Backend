package core.kobaco.infra.jpa.advertisesave;

import core.kobaco.domain.advertisesave.AdvertiseCapture;
import core.kobaco.domain.advertisesave.AdvertiseCaptureRepository;
import core.kobaco.infra.jpa.advertisesave.repository.AdvertiseCaptureJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdvertiseCaptureRepositoryImpl implements AdvertiseCaptureRepository {
    private final AdvertiseCaptureJpaRepository advertiseCaptureJpaRepository;
    private final SaveMapper saveMapper;
    @Override
    public AdvertiseCapture save(AdvertiseCapture advertiseCapture) {
        return saveMapper.toDomain(advertiseCaptureJpaRepository.save(saveMapper.toEntity(advertiseCapture)));
    }

    @Override
    public Optional<AdvertiseCapture> findByFileId(Long fileId) {
        return advertiseCaptureJpaRepository.findByImageFileId(fileId).map(saveMapper::toDomain);
    }
}
