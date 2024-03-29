package core.kobaco.infra.jpa.file;

import core.kobaco.domain.file.File;
import core.kobaco.domain.file.FileRepository;
import core.kobaco.domain.file.FileType;
import core.kobaco.infra.jpa.file.repository.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepository {
    private final FileJpaRepository fileJpaRepository;
    private final FileMapper fileMapper;

    @Override
    public Optional<File> findRootDirectoryByNamespaceId(Long namespaceId) {
        return fileJpaRepository.findRootDirectoryByNamespaceId(namespaceId)
            .map(fileMapper::toDomain);
    }

    @Override
    public Optional<File> findCaptureDirectoryByUserId(Long userId) {
        return fileJpaRepository.findRootSubDirectoryByUserIdAndFileType(userId, FileType.CAPTURE_DIRECTORY)
            .map(fileMapper::toDomain);
    }

    @Override
    public Optional<File> findBasicDirectoryByUserId(Long userId) {
        return fileJpaRepository.findRootSubDirectoryByUserIdAndFileType(userId, FileType.BASIC_DIRECTORY)
            .map(fileMapper::toDomain);
    }

    @Override
    public Optional<File> findByFileName(String fileName) {
        return fileJpaRepository.findByFileName(fileName)
            .map(fileMapper::toDomain);
    }

    @Override
    public File save(File file) {
        return fileMapper.toDomain(fileJpaRepository.save(fileMapper.toEntity(file)));
    }

    @Override
    public List<File> findAllByFileId(Long fileId) {
        return fileJpaRepository.findAllByParentFileId(fileId).stream()
            .map(fileMapper::toDomain)
            .toList();
    }

    @Override
    public Optional<File> findByFileId(Long directoryId) {
        return fileJpaRepository.findById(directoryId)
            .map(fileMapper::toDomain);
    }

    @Override
    public void update(File file) {
        fileJpaRepository.save(fileMapper.toEntity(file));
    }

    @Override
    public void delete(File directory) {
        fileJpaRepository.delete(fileMapper.toEntity(directory));
    }
}
