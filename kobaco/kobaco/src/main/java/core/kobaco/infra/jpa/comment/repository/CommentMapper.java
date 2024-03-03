package core.kobaco.infra.jpa.comment.repository;

import core.kobaco.domain.comment.Comment;
import core.kobaco.domain.comment.CommentRepository;
import core.kobaco.domain.user.UserRepository;
import core.kobaco.infra.comment.CommentJpaRepository;
import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import core.kobaco.infra.jpa.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final UserRepository userRepository;

    public CommentEntity toEntity(Comment comment) {
        UserEntity userEntity = userRepository.findById(comment.getCommenter().getId())
                .map(user -> UserEntity.from(user.getId()))
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return CommentEntity.of(comment.getContent(), userEntity);

    }

    public Comment toDomain(CommentEntity commentEntity) {
        return new Comment(
                commentEntity.getId(),
                commentEntity.getContent(),
                commentEntity.getUser()
        );
    }

    public List<Comment> toDomainList(List<CommentEntity> commentEntities) {
        return commentEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Repository
    public static class CommentRepositoryImpl implements CommentRepository {
        private final CommentJpaRepository commentJpaRepository;
        private final CommentMapper commentMapper;

        public CommentRepositoryImpl(CommentJpaRepository commentJpaRepository, CommentMapper commentMapper) {
            this.commentJpaRepository = commentJpaRepository;
            this.commentMapper = commentMapper;
        }

        @Override
        public Optional<Comment> findById(Long commentId) {
            return commentJpaRepository.findById(commentId)
                    .map(commentMapper::toDomain);
        }

        @Override
        public Comment save(Comment comment) {
            CommentEntity commentEntity = commentMapper.toEntity(comment);
            commentEntity = commentJpaRepository.save(commentEntity);
            return commentMapper.toDomain(commentEntity);
        }

        @Override
        public List<Comment> findAll() {
            List<CommentEntity> commentEntities = commentJpaRepository.findAll();
            return commentMapper.toDomainList(commentEntities);
        }
    }
}
