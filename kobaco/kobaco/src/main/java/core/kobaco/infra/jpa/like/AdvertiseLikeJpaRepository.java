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
        order by count(al.advertisement.id) desc, al.advertisement.id desc
""")
    List<Long> findTopLankAdvertiseId(Pageable pageable);

    @Query("""
        select al.advertisement.id
        from AdvertiseLikeEntity al
        join AdvertisementKeywordEntity ak on ak.advertisement=al.advertisement and ak.keyword in (
            select ke
            from KeywordEntity ke
            where ke.description in :keywordList
        )
        group by al.advertisement.id
        having count(distinct ak.keyword) = :keywordSize
        order by count(al.advertisement.id) desc, al.advertisement.id desc
        """)
    List<Long> findTopLankAdvertiseIdWithKeyword(Pageable pageable, List<String> keywordList, Long keywordSize);
}
