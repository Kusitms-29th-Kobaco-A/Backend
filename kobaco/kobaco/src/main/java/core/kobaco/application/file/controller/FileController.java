package core.kobaco.application.file.controller;

import core.kobaco.application.file.service.FileService;
import core.kobaco.application.file.service.dto.DirectoryCreateRequest;
import core.kobaco.application.file.service.dto.DirectoryDetailResponse;
import core.kobaco.application.file.service.dto.DirectoryUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/namespaces")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @Operation(summary = "디렉토리 조회, 빈값으로 조회시 최상위 디렉토리 조회", description =
        """
        디렉토리 조회, 
        빈값으로 조회시 최상위 디렉토리 조회
        디렉토리 id값을 넣어서 조회하면 해당 디렉토리의 하위 디렉토리와 파일을 조회합니다.
        
        조회시 디렉토리, 캡쳐 이미지, 광고 조회할 수 있습니다.
        
        File Type:
        - DIRECTORY: 디렉토리   
        - IMAGE: 캡쳐 이미지
        - ADVERTISE: 광고
        - BASIC_DIRECTORY: 기본 디렉토리
            """)
    @GetMapping(path = {"/{directoryId}", ""})
    public DirectoryDetailResponse getFiles(
        @PathVariable(required = false) Long directoryId) {
        return fileService.getFiles(directoryId);
    }


    @Operation(summary = "디렉토리 생성")
    @PostMapping("/{directoryId}")
    public void createDirectory(
        @PathVariable Long directoryId,
        @RequestBody DirectoryCreateRequest request) {
        fileService.createDirectory(directoryId, request);
    }

    @Operation(summary = "디렉토리 이름 수정")
    @PatchMapping("/{directoryId}")
    public void updateDirectoryName(
        @PathVariable Long directoryId,
        @RequestBody DirectoryUpdateRequest request) {
        fileService.updateDirectoryName(directoryId, request);
    }

}
