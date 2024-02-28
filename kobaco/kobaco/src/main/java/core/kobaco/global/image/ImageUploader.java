package core.kobaco.global.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploader {
    List<String> uploadImage(List<MultipartFile> multipartFile);
    void deleteImage(String fileName);
}
