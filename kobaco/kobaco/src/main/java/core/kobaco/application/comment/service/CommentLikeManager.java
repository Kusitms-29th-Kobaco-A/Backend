package core.kobaco.application.comment.service;

public interface CommentLikeManager {
    Long getLikeCount(Long commentId);
    Boolean isLike(Long commentId);
    void like(Long commentId, Long userId);

    }


