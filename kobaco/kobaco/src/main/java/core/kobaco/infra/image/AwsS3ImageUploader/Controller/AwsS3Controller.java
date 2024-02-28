package core.kobaco.infra.image.AwsS3ImageUploader.Controller;

import core.kobaco.infra.image.AwsS3ImageUploader.Service.AwsS3Service;
import core.kobaco.global.ApiResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;

    @ApiOperation(value = "Amazon S3에 이미지 업로드", notes = "Amazon S3에 이미지 업로드 ")
    @PostMapping("/image")
    public ResponseEntity<List<String>> uploadImage(@ApiParam(value="img 파일들(여러 파일 업로드 가능)", required = true) @RequestPart List<MultipartFile> multipartFile) {
        return ApiResponse.success(awsS3Service.uploadImage(multipartFile));
    }

    @ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제", notes = "Amazon S3에 업로드된 이미지 삭제")
    @DeleteMapping("/image")
    public ResponseEntity<Void> deleteImage(@ApiParam(value="img 파일 하나 삭제", required = true) @RequestParam String fileName) {
        awsS3Service.deleteImage(fileName);
        return ApiResponse.success(null);
    }
}
