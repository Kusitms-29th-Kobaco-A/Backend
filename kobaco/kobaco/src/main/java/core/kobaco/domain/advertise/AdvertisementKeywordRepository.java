package core.kobaco.domain.advertise;

import java.util.List;

public interface AdvertisementKeywordRepository {
    List<AdvertisementKeyword> findAllByAdvertiseId(Long advertiseId);

    AdvertisementKeyword save(AdvertisementKeyword advertisementKeyword);

    void saveAll(List<AdvertisementKeyword> advertisementKeywords);
}
