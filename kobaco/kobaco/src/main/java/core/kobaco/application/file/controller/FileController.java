package core.kobaco.application.file.controller;

import core.kobaco.application.file.service.FileService;
import core.kobaco.application.file.service.dto.DirectoryCreateRequest;
import core.kobaco.application.file.service.dto.DirectoryDetailResponse;
import core.kobaco.application.file.service.dto.DirectoryUpdateRequest;
import core.kobaco.application.file.service.dto.NamespaceDetailResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/namespaces")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping(path = {"/{directoryId}", "/"})
    public DirectoryDetailResponse getFiles(
        @Parameter(required = false) @PathVariable(required = false) Long directoryId) {
        return fileService.getFiles(directoryId);
    }


    @PostMapping("/{directoryId}")
    public void createDirectory(
        @PathVariable Long directoryId,
        @RequestBody DirectoryCreateRequest request) {
        fileService.createDirectory(directoryId, request);
    }

    @PatchMapping("/{directoryId}")
    public void updateDirectoryName(
        @PathVariable Long directoryId,
        @RequestBody DirectoryUpdateRequest request) {
        fileService.updateDirectoryName(directoryId, request);
    }

}
