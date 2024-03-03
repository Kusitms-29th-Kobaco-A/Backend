package core.kobaco.domain.comment;

import lombok.*;

@Getter
@AllArgsConstructor
public class Comment {
    private Long commentId;
    private String content;
    private Long commenterId;

    public static Comment of (Long commentId, String content, Long commenterId) {
        return new Comment(commentId, content, commenterId);
    }

}

