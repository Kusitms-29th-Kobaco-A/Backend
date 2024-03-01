package core.kobaco.application.advertise.controller;

import core.kobaco.application.advertise.service.AdvertiseService;
import core.kobaco.application.advertise.service.dto.AdvertiseCreateRequest;
import core.kobaco.application.advertise.service.dto.AdvertiseDetailResponse;
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
    }

    @PostMapping("/{advertiseId}/save")
    public void saveAdvertise(@PathVariable Long advertiseId) {
    }

    @PostMapping("/{advertiseId}/capture")
    public void captureAdvertise(@PathVariable Long advertiseId) {
    }
}
