package core.kobaco.application.comment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDetail {
    private Long commentId;
    private String content;

    public CommentDetail(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public static CommentDetail of(Long commentId, String content) {
        return new CommentDetail(commentId, content);
    }
}