package core.kobaco.infra.jpa.advertisesave;

import core.kobaco.domain.advertisesave.AdvertiseSave;
import core.kobaco.domain.advertisesave.AdvertiseSaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdvertiseSaveRepositoryImpl implements AdvertiseSaveRepository {
    private final AdvertiseSaveJpaRepository advertiseSaveJpaRepository;
    private final SaveMapper saveMapper;
    @Override
    public AdvertiseSave save(AdvertiseSave advertiseSave) {
        return saveMapper.toDomain(advertiseSaveJpaRepository.save(saveMapper.toEntity(advertiseSave)));
    }
}
