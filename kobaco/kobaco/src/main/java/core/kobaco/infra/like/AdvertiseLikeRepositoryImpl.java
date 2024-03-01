package core.kobaco.infra.like;

import core.kobaco.domain.like.AdvertiseLike;
import core.kobaco.domain.like.AdvertiseLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdvertiseLikeRepositoryImpl implements AdvertiseLikeRepository {
    private final AdvertiseLikeJpaRepository advertiseLikeJpaRepository;
    private final LikeMapper likeMapper;

    @Override
    public Long countByAdvertisementId(Long advertiseId) {
        return advertiseLikeJpaRepository.countByAdvertisementId(advertiseId);
    }

    @Override
    public Boolean isLike(Long advertiseId, Long userId) {
        return advertiseLikeJpaRepository.existsByAdvertisementIdAndUserId(advertiseId, userId);
    }

    @Override
    public AdvertiseLike save(AdvertiseLike advertiseLike) {
        return likeMapper.toDomain(advertiseLikeJpaRepository.save(likeMapper.toEntity(advertiseLike)));
    }

    @Override
    public void delete(AdvertiseLike advertiseLike) {
        advertiseLikeJpaRepository.delete(likeMapper.toEntity(advertiseLike));
    }

    @Override
    public Optional<AdvertiseLike> findByAdvertisementIdAndUserId(Long advertiseId, Long userId) {
        return advertiseLikeJpaRepository.findByAdvertisementIdAndUserId(advertiseId, userId).map(likeMapper::toDomain);
    }

}
