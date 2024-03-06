package core.kobaco.domain.commentlike.service;

import lombok.Getter;


@Getter
public class CommentLike {
    private Long likeId;
    private Long commentId;
    private Long userId;

    private CommentLike(Long likeId, Long commentId, Long userId) {
        this.likeId = likeId;
        this.commentId = commentId;
        this.userId = userId;
    }

    public static CommentLike of(Long likeId, Long commentId, Long userId) {
        return new CommentLike(likeId, commentId, userId);
    }
}

