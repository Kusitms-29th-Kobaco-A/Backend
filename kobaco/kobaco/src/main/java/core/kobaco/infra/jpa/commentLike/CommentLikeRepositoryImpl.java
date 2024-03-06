package core.kobaco.infra.jpa.commentLike;

import core.kobaco.domain.commentlike.service.CommentLike;
import core.kobaco.domain.commentlike.service.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepository {
    private final CommentLikeJpaRepository commentLikeJpaRepository;
    private final CommentLikeMapper likeMapper;

    @Override
    public Long countByCommentId(Long commentId) {
        return commentLikeJpaRepository.countByCommentId(commentId);
    }

    @Override
    public Boolean isLike(Long commentId, Long userId) {
        return commentLikeJpaRepository.existsByCommentIdAndUserId(commentId, userId);
    }

    @Override
    public CommentLike save(CommentLike commentLike) {
        return likeMapper.toDomain(commentLikeJpaRepository.save(likeMapper.toEntity(commentLike)));
    }

    @Override
    public void delete(CommentLike commentLike) {
        commentLikeJpaRepository.delete(likeMapper.toEntity(commentLike));
    }

    @Override
    public Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId) {
        return commentLikeJpaRepository.findByCommentIdAndUserId(commentId, userId).map(likeMapper::toDomain);
    }
}
