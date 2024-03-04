package core.kobaco.infra.jpa.advertisement;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementRepository;
import core.kobaco.infra.jpa.advertisement.repository.AdvertisementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdvertisementRepositoryImpl implements AdvertisementRepository {
    private final AdvertisementJpaRepository advertisementJpaRepository;
    private final AdvertiseMapper advertiseMapper;
    @Override
    public Optional<Advertisement> findById(Long advertiseId) {
        return advertisementJpaRepository.findById(advertiseId)
            .map(advertiseMapper::toDomain);
    }

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertiseMapper.toDomain(advertisementJpaRepository.save(advertiseMapper.toEntity(advertisement)));
    }

    @Override
    public Page<Advertisement> findAll(Pageable pageable) {
        return advertisementJpaRepository.findAll(pageable)
            .map(advertiseMapper::toDomain);
    }

    @Override
    public Page<Advertisement> findSavedAllByUserId(Pageable pageable, Long userId) {
        return advertisementJpaRepository.findSavedAllByUserId(pageable, userId)
            .map(advertiseMapper::toDomain);
    }
}
