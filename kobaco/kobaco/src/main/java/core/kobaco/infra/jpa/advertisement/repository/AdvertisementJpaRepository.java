package core.kobaco.infra.jpa.advertisement.repository;

import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
