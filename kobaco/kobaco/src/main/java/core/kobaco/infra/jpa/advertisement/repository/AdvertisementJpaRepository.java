package core.kobaco.infra.jpa.advertisement.repository;

import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementJpaRepository extends JpaRepository<AdvertisementEntity, Long> {

    @Query("""
                select ae
                from AdvertisementEntity ae
                join AdvertiseSaveEntity ase on ae.id = ase.advertisement.id
                where ase.file.id in (
                    select fe.id
                    from FileEntity fe
                    join NamespaceEntity  ne on fe.namespace=ne and ne.user.id = :userId
                )
                order by ase.id desc
        """)
    Page<AdvertisementEntity> findSavedAllByUserId(Pageable pageable, @Param("userId") Long userId);

    @Query("""
        select ae
        from AdvertisementEntity ae
        join AdvertisementKeywordEntity ak on ak.advertisement=ae and ak.keyword in (
        select ke
        from KeywordEntity ke
        where ke.description in :keywordList
        )
        group by ae.id
        having count(distinct ak.keyword) = :keywordSize
        order by ae.uploadDate desc
        """)
    Page<AdvertisementEntity> findAllWithKeyword(Pageable pageable, List<String> keywordList, Long keywordSize);


    @Query("""
        select distinct ae
        from AdvertisementEntity ae
        join AdvertisementKeywordEntity ak on ak.advertisement=ae and ak.keyword in (
            select ke
            from KeywordEntity ke
            join AdvertisementKeywordEntity ake on ake.keyword=ke and ake.advertisement.id = :advertiseId
        )
        where ae.id != :advertiseId
""")
    Page<AdvertisementEntity> findAllWithKeywordByAdvertiseId(Pageable pageable, Long advertiseId);


    @Query("""
        select distinct ae
        from AdvertisementEntity ae
        where ae.makerCompany = :makerCompany and ae.id != :advertiseId
        and ae.id not in(
            select ake.advertisement.id
            from AdvertisementKeywordEntity ake
            where ake.keyword in (
                select ke
                from KeywordEntity ke
                join AdvertisementKeywordEntity ake on ake.keyword=ke and ake.advertisement.id = :advertiseId
            )
       )
        """)
    Page<AdvertisementEntity> findAllByMakerCompanyAndAdvertiseId(Pageable pageable, String makerCompany, Long advertiseId);

    @Query("""
        select ae
        from AdvertisementEntity ae
        join AdvertisementKeywordEntity ak on ak.advertisement=ae and ak.keyword in (
            select ke
            from KeywordEntity ke
            where ke.description in :keywordList
        )
        join KeywordEntity ke on ke.description in :keywordList
        left join AdvertiseLikeEntity ale on ale.advertisement=ae
        group by ae.id
        having count(distinct ak.keyword) = :keywordSize
        order by count(ale.advertisement) desc
        """)
    Page<AdvertisementEntity> findAllWithKeywordOrderByPopularity(Pageable pageable, @Param("keywordList") List<String> keywordList, Long keywordSize);

    @Query("""
        select ae
        from AdvertisementEntity ae
        left join AdvertiseLikeEntity ale on ale.advertisement=ae
        group by ae
        order by count(ale.advertisement) desc
        """)
    Page<AdvertisementEntity> findAllOrderByPopularity(Pageable pageable);

    @Query("""
        select ae
        from AdvertisementEntity ae
        order by ae.viewCount desc
        """)
    Page<AdvertisementEntity> findAllOrderByViewCount(Pageable pageable);


    @Query("""
        select ae
        from AdvertisementEntity ae
        join AdvertisementKeywordEntity ak on ak.advertisement=ae and ak.keyword in (
        select ke
        from KeywordEntity ke
        where ke.description in :keywordList
        )
        group by ae.id
        having count(distinct ak.keyword) = :keywordSize
        order by ae.viewCount desc
        """)
    Page<AdvertisementEntity> findAllWithKeywordOrderByViewCount(Pageable pageable, List<String> keywordList, Long keywordSize);


    @Modifying
    @Query("""
        update AdvertisementEntity ae
        set ae.viewCount = ae.viewCount + 1
        where ae.id = :advertiseId
        """)
    void updateViewCount(Long advertiseId);

    @Query("""
        select ae
        from AdvertisementEntity ae
        order by ae.uploadDate desc
        """)
    Page<AdvertisementEntity> findAllOrderByUploadDateDesc(Pageable pageable);
}
