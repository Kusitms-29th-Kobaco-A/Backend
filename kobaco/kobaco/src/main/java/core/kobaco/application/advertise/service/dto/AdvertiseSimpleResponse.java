package core.kobaco.application.advertise.service.dto;

import java.util.List;

public record AdvertiseSimpleResponse (
    String previewImageUrl,
    String title,
    List<String> keywords
){
}
