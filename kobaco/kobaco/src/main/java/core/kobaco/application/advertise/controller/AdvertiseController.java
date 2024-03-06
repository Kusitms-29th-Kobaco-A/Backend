package core.kobaco.application.advertise.controller;

import core.kobaco.application.advertise.service.AdvertiseService;
import core.kobaco.application.advertise.service.dto.*;
import core.kobaco.domain.advertise.OrderType;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertises")
public class AdvertiseController {
    private final AdvertiseService advertiseService;

    @Operation(summary = "광고 생성")
    @PostMapping
    public void createAdvertise(
        @RequestBody AdvertiseCreateRequest request) {
        advertiseService.createAdvertise(request);
    }

    @Operation(summary = "전체 광고", description = """
        query param을 통해서 검색어를 전달받아 검색어에 해당하는 광고 목록을 조회합니다.
        컨셉, 상업군 상관없이 keywordList에 전달하면 됩니다.
        
        orderType에 따라서 최신순, 인기순, 조회수 순으로 정렬할 수 있습니다.
        - RECENT: 최신순
        - POPULAR: 인기순
        - VIEW: 조회수순
        """)
    @GetMapping
    public Page<AdvertiseSimpleResponse> getAdvertiseList(
        Pageable pageable,
        @RequestParam(required = false) List<String> keywordList,
        @RequestParam OrderType orderType) {
        return advertiseService.getAdvertiseList(
            PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending()),
            keywordList,
            orderType
        );
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

    @Operation(summary = "트랜드 광고 조회")
    @GetMapping("/trends")
    public Page<TrendAdvertiseSimpleResponse> getTrendAdvertises(Pageable pageable){
        return advertiseService.getTrendAdvertiseList(pageable);
    }

    @Operation(summary = "트랜드 광고 만들기", description = """
        특정 광고를 요청 보내면 해당 광고를 트랜드 광고로 만듭니다.""")
    @PostMapping("/{advertiseId}/trends")
    public void trendAdvertise(@PathVariable Long advertiseId,
                               @RequestBody AdvertiseTrendCreateRequest request){
        advertiseService.trendAdvertise(advertiseId, request);
    }

    @Operation(summary = "광고 추천", description = """
       키워드가 하나라도 일치하는 광고를 추천합니다.
        만약 추천된 광고가 없다면, 광고의 제작사와 동일한 광고를 추천합니다. 
       """)
    @GetMapping("/{advertiseId}/recommend")
    public Page<AdvertiseSimpleResponse> getRecommendAdvertises(@PathVariable Long advertiseId, Pageable pageable){
        return advertiseService.getRecommendAdvertiseList(pageable, advertiseId);
    }

}
