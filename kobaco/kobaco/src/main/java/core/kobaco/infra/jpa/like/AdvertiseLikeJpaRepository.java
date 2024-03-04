package core.kobaco.infra.jpa.like;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdvertiseLikeJpaRepository extends JpaRepository<AdvertiseLikeEntity, Long> {

    Long countByAdvertisementId(Long advertiseId);

    Boolean existsByAdvertisementIdAndUserId(Long advertiseId, Long userId);

    Optional<AdvertiseLikeEntity> findByAdvertisementIdAndUserId(Long advertiseId, Long userId);

    @Query("""
        select al.advertisement.id
        from AdvertiseLikeEntity al
        group by al.advertisement.id
        order by count(al.advertisement.id) desc
""")
    List<Long> findTopLankAdvertiseId(Pageable pageable);
}
