package core.kobaco.application.comment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDetailResponse {
    private String content;
    private String userEmail;

    public CommentDetailResponse(String content, String userEmail) {
        this.content = content;
        this.userEmail = userEmail;
    }

    public static CommentDetailResponse of(String content, String userEmail) {
        return new CommentDetailResponse(content, userEmail);
    }
}