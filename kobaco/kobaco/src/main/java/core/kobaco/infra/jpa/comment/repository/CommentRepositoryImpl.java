package core.kobaco.infra.jpa.comment.repository;

import core.kobaco.domain.comment.Comment;

import core.kobaco.domain.comment.CommentRepository;
import core.kobaco.infra.comment.CommentJpaRepository;
import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public class CommentRepositoryImpl implements CommentRepository {
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
    public Comment save(Comment comment, Long advertiseId) {
        CommentEntity commentEntity = commentMapper.toEntity(comment, comment.getCommenterId(), advertiseId);
        commentEntity = commentJpaRepository.save(commentEntity);
        return commentMapper.toDomain(commentEntity);
    }

    @Override
    public Page<Comment> findAllByAdvertiseId(Long advertiseId, Pageable pageable) {
        Page<CommentEntity> commentEntitiesPage = commentJpaRepository.findAllByAdvertiseId(advertiseId, pageable);
        return commentEntitiesPage.map(commentMapper::toDomain);
    }

}

