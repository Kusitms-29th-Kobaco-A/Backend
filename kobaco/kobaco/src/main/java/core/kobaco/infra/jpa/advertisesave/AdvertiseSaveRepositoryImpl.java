package core.kobaco.infra.jpa.advertisesave;

import core.kobaco.domain.advertisesave.AdvertiseSave;
import core.kobaco.domain.advertisesave.AdvertiseSaveRepository;
import core.kobaco.infra.jpa.advertisesave.repository.AdvertiseSaveJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdvertiseSaveRepositoryImpl implements AdvertiseSaveRepository {
    private final AdvertiseSaveJpaRepository advertiseSaveJpaRepository;
    private final SaveMapper saveMapper;
    @Override
    public AdvertiseSave save(AdvertiseSave advertiseSave) {
        return saveMapper.toDomain(advertiseSaveJpaRepository.save(saveMapper.toEntity(advertiseSave)));
    }

    @Override
    public Optional<AdvertiseSave> findByFileId(Long fileId) {
        return advertiseSaveJpaRepository.findByFileId(fileId).map(saveMapper::toDomain);
    }
}
