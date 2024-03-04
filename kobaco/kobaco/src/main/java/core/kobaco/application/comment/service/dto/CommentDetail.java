package core.kobaco.application.comment.service.dto;

import core.kobaco.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDetail {
    private Long commentId;
    private String content;
    private Long userId;

    public CommentDetail(Long commentId, String content, Long userId) {
        this.commentId = commentId;
        this.content = content;
        this.userId = userId;
    }
    public static CommentDetail of(Long commentId, String content, Long userId){
        return new CommentDetail(commentId, content, userId);
    }
}
