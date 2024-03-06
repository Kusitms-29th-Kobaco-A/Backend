package core.kobaco.domain.commentlike.service;

public interface CommentLikeManager {
    Long getLikeCount(Long commentId);
    Boolean isLike(Long commentId);
    void like(Long commentId, Long userId);

    }


