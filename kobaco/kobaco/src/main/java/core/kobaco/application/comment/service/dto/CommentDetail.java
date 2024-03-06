package core.kobaco.application.comment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDetail {
    private String content;

    public CommentDetail(String content) {
        this.content = content;
    }

    public static CommentDetail of(String content) {
        return new CommentDetail(content);

    }
}