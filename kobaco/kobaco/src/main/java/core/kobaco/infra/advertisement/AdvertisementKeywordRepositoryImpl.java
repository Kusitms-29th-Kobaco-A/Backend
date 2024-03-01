package core.kobaco.infra.advertisement;

import core.kobaco.domain.advertise.AdvertisementKeyword;
import core.kobaco.domain.advertise.AdvertisementKeywordRepository;
import core.kobaco.infra.advertisement.repository.AdvertisementKeywordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdvertisementKeywordRepositoryImpl implements AdvertisementKeywordRepository {
    private final AdvertisementKeywordJpaRepository advertisementKeywordJpaRepository;
    private final AdvertiseMapper advertiseMapper;
    @Override
    public List<AdvertisementKeyword> findAllByAdvertiseId(Long advertiseId) {
        return advertisementKeywordJpaRepository.findAllByAdvertisementId(advertiseId).stream()
            .map(advertiseMapper::toDomain)
            .toList();
    }

    @Override
    public AdvertisementKeyword save(AdvertisementKeyword advertisementKeyword) {
        return advertiseMapper.toDomain(advertisementKeywordJpaRepository.save(advertiseMapper.toEntity(advertisementKeyword)));
    }

    @Override
    public void saveAll(List<AdvertisementKeyword> advertisementKeywords) {
        advertisementKeywordJpaRepository.saveAll(advertisementKeywords.stream().map(advertiseMapper::toEntity).toList());
    }
}
