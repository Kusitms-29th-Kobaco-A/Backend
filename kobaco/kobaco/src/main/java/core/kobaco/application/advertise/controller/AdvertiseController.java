package core.kobaco.application.advertise.controller;

import core.kobaco.application.advertise.service.AdvertiseService;
import core.kobaco.application.advertise.service.dto.AdvertiseCreateRequest;
import core.kobaco.application.advertise.service.dto.AdvertiseDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseLikeDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertises")
public class AdvertiseController {
    private final AdvertiseService advertiseService;

    @Operation(summary = "광고 생성")
    @PostMapping
    public void createAdvertise(AdvertiseCreateRequest request) {
        advertiseService.createAdvertise(request);
    }

    @Operation(summary = "광고 목록 조회")
    @GetMapping
    public Page<String> getAdvertiseList(Pageable pageable) {
        return null;
    }

    @Operation(summary = "광고 상세 조회")
    @GetMapping("/{advertiseId}")
    public AdvertiseDetailResponse getAdvertise(@PathVariable Long advertiseId) {
        return advertiseService.getAdvertise(advertiseId);
    }

    @Operation(summary = "광고 좋아요", description = """
        좋아요가 아니면 좋아요를 추가하고, 좋아요면 좋아요를 취소합니다.
        """)
    @PatchMapping("/{advertiseId}/like")
    public void likeAdvertise(@PathVariable Long advertiseId) {
        advertiseService.likeAdvertise(advertiseId);
    }

    @Operation(summary = "광고 좋아요 조회")
    @GetMapping("/{advertiseId}/like")
    public AdvertiseLikeDetailResponse getAdvertiseLikeCount(@PathVariable Long advertiseId) {
        return advertiseService.getAdvertiseLikeCount(advertiseId);
    }
}
