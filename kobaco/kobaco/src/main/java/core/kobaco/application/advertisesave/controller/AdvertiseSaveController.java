package core.kobaco.application.advertisesave.controller;

import core.kobaco.application.advertisesave.service.AdvertiseSaveService;
import core.kobaco.application.advertisesave.service.dto.AdvertiseSaveRequest;
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


    @PostMapping("/advertises")
    public void saveAdvertise(@RequestBody AdvertiseSaveRequest request){
        advertiseSaveService.saveAdvertise(request.directoryId(), request.advertiseId());
    }

    @PostMapping("/advertises/capture")
    public void saveAdvertiseCapture(){

    }
}
