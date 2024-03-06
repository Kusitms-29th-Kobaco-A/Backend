package core.kobaco.infra.jpa.advertisement;

import core.kobaco.domain.advertise.Advertisement;
import core.kobaco.domain.advertise.AdvertisementRepository;
import core.kobaco.domain.advertise.OrderType;
import core.kobaco.infra.jpa.advertisement.repository.AdvertisementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
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

    @Override
    public Page<Advertisement> findAllWithKeyword(Pageable pageable, List<String> keywordList, OrderType orderType) {
        if(Objects.isNull(keywordList)||keywordList.isEmpty()){
            if(orderType.equals(OrderType.RECENT)){
                return advertisementJpaRepository.findAll(pageable)
                    .map(advertiseMapper::toDomain);
            }
            if(orderType.equals(OrderType.POPULAR)){
                return advertisementJpaRepository.findAllOrderByPopularity(pageable)
                    .map(advertiseMapper::toDomain);
            }
            if(orderType.equals(OrderType.VIEW_COUNT)){
                return advertisementJpaRepository.findAllOrderByViewCount(pageable)
                    .map(advertiseMapper::toDomain);
            }
        }
        if(orderType.equals(OrderType.RECENT)){
            return advertisementJpaRepository.findAllWithKeyword(pageable, keywordList, (long) keywordList.size())
                .map(advertiseMapper::toDomain);
        }
        if(orderType.equals(OrderType.POPULAR)){
            return advertisementJpaRepository.findAllWithKeywordOrderByPopularity(pageable, keywordList, (long) keywordList.size())
                .map(advertiseMapper::toDomain);
        }
        return advertisementJpaRepository.findAllWithKeywordOrderByViewCount(pageable, keywordList, (long) keywordList.size())
            .map(advertiseMapper::toDomain);
    }

    @Override
    public Page<Advertisement> findAllWithKeywordByAdvertiseId(Pageable pageable, Long advertiseId) {
        return advertisementJpaRepository.findAllWithKeywordByAdvertiseId(pageable, advertiseId)
            .map(advertiseMapper::toDomain);
    }

    @Override
    public Page<Advertisement> findAllByMakerCompanyAndAdvertiseId(Pageable pageable, String makerCompany, Long advertiseId) {
        return advertisementJpaRepository.findAllByMakerCompanyAndAdvertiseId(pageable, makerCompany, advertiseId)
            .map(advertiseMapper::toDomain);
    }

    @Override
    public void upViewCount(Long advertiseId) {
        advertisementJpaRepository.updateViewCount(advertiseId);
    }
}
