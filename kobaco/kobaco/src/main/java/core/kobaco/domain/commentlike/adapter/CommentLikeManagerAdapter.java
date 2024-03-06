package core.kobaco.domain.commentlike.adapter;

import core.kobaco.domain.commentlike.service.CommentLikeManager;
import core.kobaco.domain.commentlike.service.CommentLike;
import core.kobaco.domain.commentlike.service.CommentLikeRepository;
import core.kobaco.domain.user.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikeManagerAdapter implements CommentLikeManager {
    private final UserUtils userUtils;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    public Long getLikeCount(Long commentId) {
        return commentLikeRepository.countByCommentId(commentId);
    }

    @Override
    public Boolean isLike(Long commentId) {
        return commentLikeRepository.isLike(commentId, userUtils.getRequestUserId());
    }

    @Override
    public void like(Long commentId, Long userId) {
        commentLikeRepository.findByCommentIdAndUserId(commentId, userId)
                .ifPresentOrElse(
                        commentLikeRepository::delete,
                        () -> commentLikeRepository.save(CommentLike.of(null, commentId, userId))
                );
    }
}
