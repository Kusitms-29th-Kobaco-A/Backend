package core.kobaco.application.advertisesave.controller;

import core.kobaco.application.advertisesave.service.AdvertiseSaveService;
import core.kobaco.application.advertisesave.service.dto.AdvertiseSaveRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saves")
@RequiredArgsConstructor
public class AdvertiseSaveController {
    private final AdvertiseSaveService advertiseSaveService;


    @Operation(summary = "광고 저장",
    description = """
        광고를 저장할 때, 광고 id와 파일 id를 통해 저장합니다.
        """)
    @PostMapping("/advertises")
    public void saveAdvertise(@RequestBody AdvertiseSaveRequest request){
        advertiseSaveService.saveAdvertise(request.directoryId(), request.advertiseId());
    }

    @Operation(summary = "광고 캡쳐 저장")
    @PostMapping("/advertises/capture")
    public void saveAdvertiseCapture(){

    }
}
