package core.kobaco.application.advertise.controller;

import core.kobaco.application.advertise.service.AdvertiseService;
import core.kobaco.application.advertise.service.dto.AdvertiseCreateRequest;
import core.kobaco.application.advertise.service.dto.AdvertiseDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseLikeDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<AdvertiseSimpleResponse> getAdvertiseList(Pageable pageable) {
        return advertiseService.getAdvertiseList(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()));
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


    @Operation(summary = "내가 찜한 영상")
    @GetMapping("/saves")
    public Page<AdvertiseSimpleResponse> getSaveAdvertises(Pageable pageable){
        return advertiseService.getSaveAdvertiseList(pageable);
    }

    @Operation(summary = "인기 급상승 영상 조회")
    @GetMapping("/likes")
    public Page<AdvertiseSimpleResponse> getLikeAdvertises(Pageable pageable){
        return advertiseService.getLikeAdvertiseList(pageable);
    }
}
