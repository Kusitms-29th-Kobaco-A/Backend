package core.kobaco.application.comment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateResponse {
    private String content;

    public CommentCreateResponse(String content) {
        this.content = content;
    }

    public static CommentCreateResponse of(String content) {
        return new CommentCreateResponse(content);
    }
}
