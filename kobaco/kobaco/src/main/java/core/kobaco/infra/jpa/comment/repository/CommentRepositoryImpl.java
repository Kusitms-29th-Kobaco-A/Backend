package core.kobaco.infra.jpa.comment.repository;

import core.kobaco.domain.comment.Comment;

import core.kobaco.domain.comment.CommentRepository;
import core.kobaco.infra.comment.CommentJpaRepository;
import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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
