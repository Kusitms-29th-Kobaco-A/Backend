package core.kobaco.domain.like.service;

import core.kobaco.domain.like.AdvertiseLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertiseLikeReader {
    private final AdvertiseLikeRepository likeRepository;


    public List<Long> getLikeAdvertiseIdList(Pageable pageable) {
        return likeRepository.findTopLankAdvertiseId(pageable);
    }
}
