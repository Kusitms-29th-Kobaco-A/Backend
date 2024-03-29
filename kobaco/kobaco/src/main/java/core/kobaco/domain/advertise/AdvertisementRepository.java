package core.kobaco.domain.advertise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository {
    Optional<Advertisement> findById(Long advertiseId);

    Advertisement save(Advertisement advertisement);

    Page<Advertisement> findAll(Pageable pageable);

    Page<Advertisement> findSavedAllByUserId(Pageable pageable, Long userId);

    Page<Advertisement> findAllWithKeyword(Pageable pageable, List<String> keywordList, OrderType orderType);

    Page<Advertisement> findAllWithKeywordByAdvertiseId(Pageable pageable, Long advertiseId);

    Page<Advertisement> findAllByMakerCompanyAndAdvertiseId(Pageable pageable, String makerCompany, Long advertiseId);

    void upViewCount(Long advertiseId);

    Page<Advertisement> findAllOrderByViewCount(Pageable pageable, List<String> keywordList);
}
