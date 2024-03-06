package core.kobaco.application.comment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {
    private String content;

    public CommentCreateRequest(String content) {
        this.content = content;
    }

    public static CommentCreateRequest of(String content) {
        return new CommentCreateRequest(content);
    }
}

