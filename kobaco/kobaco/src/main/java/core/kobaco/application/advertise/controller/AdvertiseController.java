package core.kobaco.application.advertise.controller;

import core.kobaco.application.advertise.service.AdvertiseService;
import core.kobaco.application.advertise.service.dto.AdvertiseCreateRequest;
import core.kobaco.application.advertise.service.dto.AdvertiseDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseLikeDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertises")
public class AdvertiseController {
    private final AdvertiseService advertiseService;

    @PostMapping
    public void createAdvertise(AdvertiseCreateRequest request) {
        advertiseService.createAdvertise(request);
    }

    @GetMapping
    public Page<String> getAdvertiseList(Pageable pageable) {
        return null;
    }

    @GetMapping("/{advertiseId}")
    public AdvertiseDetailResponse getAdvertise(@PathVariable Long advertiseId) {
        return advertiseService.getAdvertise(advertiseId);
    }

    @PatchMapping("/{advertiseId}/like")
    public void likeAdvertise(@PathVariable Long advertiseId) {
        advertiseService.likeAdvertise(advertiseId);
    }

    @GetMapping("/{advertiseId}/like")
    public AdvertiseLikeDetailResponse getAdvertiseLikeCount(@PathVariable Long advertiseId) {
        return advertiseService.getAdvertiseLikeCount(advertiseId);
    }
}
