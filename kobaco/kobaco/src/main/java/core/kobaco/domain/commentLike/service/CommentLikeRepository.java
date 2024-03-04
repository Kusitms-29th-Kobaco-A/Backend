package core.kobaco.domain.commentLike.service;

import java.util.Optional;

public interface CommentLikeRepository {
        Long countByCommentId(Long commentId);

        Boolean isLike(Long commentId, Long userId);

        CommentLike save(CommentLike commentLike);

        void delete(CommentLike commentLike);

        Optional<CommentLike> findByCommentIdAndUserId(Long commentId, Long userId);
    }

