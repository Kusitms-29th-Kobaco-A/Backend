package core.kobaco.application.comment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentDetailResponse {
    private Long commentId;
    private String content;
    private String userEmail;

    public CommentDetailResponse(Long commentId, String content, String userEmail) {
        this.commentId = commentId;
        this.content = content;
        this.userEmail = userEmail;
    }

    public static CommentDetailResponse of(Long commentId, String content, String userEmail) {
        return new CommentDetailResponse(commentId, content, userEmail);
    }
}
