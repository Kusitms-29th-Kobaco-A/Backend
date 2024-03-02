package core.kobaco.infra.jpa.image.s3.controller;

import core.kobaco.infra.jpa.image.s3.S3ImageUploader;
import core.kobaco.global.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class AwsS3Controller {
    private final S3ImageUploader s3ImageUploader;

    @PostMapping
    public ResponseEntity<List<String>> uploadImage(
        @Parameter(description="img 파일들(여러 파일 업로드 가능)", required = true) @RequestPart List<MultipartFile> multipartFile
    ) {
        return ApiResponse.success(s3ImageUploader.uploadImage(multipartFile));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteImage(
        @Parameter(description="img 파일 하나 삭제", required = true) @RequestParam String fileName
    ) {
        s3ImageUploader.deleteImage(fileName);
        return ApiResponse.success(null);
    }
}
