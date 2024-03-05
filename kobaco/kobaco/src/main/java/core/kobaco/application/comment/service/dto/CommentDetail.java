package core.kobaco.application.comment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDetail {
    private Long commentId;
    private String content;
    private String commenterEmail;

    public CommentDetail(Long commentId, String content, String commenterEmail) {
        this.commentId = commentId;
        this.content = content;
        this.commenterEmail = commenterEmail;
    }

    public static CommentDetail of(Long commentId, String content, String commenterEmail) {
        return new CommentDetail(commentId, content, commenterEmail);
    }
}