package core.kobaco.application.advertise.controller;

import core.kobaco.application.advertise.service.AdvertiseService;
import core.kobaco.application.advertise.service.dto.AdvertiseCreateRequest;
import core.kobaco.application.advertise.service.dto.AdvertiseDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseLikeDetailResponse;
import core.kobaco.application.advertise.service.dto.AdvertiseSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

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
        List<AdvertiseSimpleResponse> advertiseList = List.of(
            new AdvertiseSimpleResponse(
                1L,
                "광고 이미지 URL",
                "광고 제목",
                Time.valueOf(LocalTime.of(0, 4,23)),
                List.of("광고 태그1", "광고 태그2")
            )
        );
        return new PageImpl<>(advertiseList, pageable, advertiseList.size());
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
