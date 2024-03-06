package core.kobaco.global.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploader {
    String uploadImage(MultipartFile multipartFile);
    List<String> uploadImageList(List<MultipartFile> multipartFile);
    void deleteImage(String fileName);
}
