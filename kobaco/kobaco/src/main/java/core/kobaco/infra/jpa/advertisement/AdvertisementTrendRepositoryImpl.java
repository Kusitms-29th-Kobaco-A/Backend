package core.kobaco.infra.jpa.advertisement;

import core.kobaco.domain.advertise.AdvertisementTrend;
import core.kobaco.domain.advertise.AdvertisementTrendRepository;
import core.kobaco.infra.jpa.advertisement.repository.AdvertisementTrendJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdvertisementTrendRepositoryImpl implements AdvertisementTrendRepository {
    private final AdvertisementTrendJpaRepository advertisementTrendJpaRepository;
    private final AdvertiseMapper advertiseMapper;
    @Override
    public AdvertisementTrend save(AdvertisementTrend advertisementTrend) {
        return advertiseMapper.toDomain(advertisementTrendJpaRepository.save(advertiseMapper.toEntity(advertisementTrend)));
    }

    @Override
    public Page<AdvertisementTrend> findAll(Pageable pageable) {
        return advertisementTrendJpaRepository.findAll(pageable).map(advertiseMapper::toDomain);
    }
}
