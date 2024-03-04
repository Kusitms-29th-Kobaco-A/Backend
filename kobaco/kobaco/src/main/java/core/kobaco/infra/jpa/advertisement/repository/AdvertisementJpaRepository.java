package core.kobaco.infra.jpa.advertisement.repository;

import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementJpaRepository extends JpaRepository<AdvertisementEntity, Long> {

    @Query("""
                select distinct ae
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
        select distinct ae
        from AdvertisementEntity ae
        join AdvertisementKeywordEntity ak on ak.advertisement=ae and ak.keyword in (
        select ke
        from KeywordEntity ke
        where ke.description in :keywordList
        )
        """)
    Page<AdvertisementEntity> findAllWithKeyword(Pageable pageable, List<String> keywordList);


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
}
