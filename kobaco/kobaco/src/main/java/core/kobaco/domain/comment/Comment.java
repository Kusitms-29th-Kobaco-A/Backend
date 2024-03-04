package core.kobaco.domain.comment;

import lombok.*;

@Getter
@AllArgsConstructor
public class Comment {
    private Long commentId;
    private String content;
    private Long commenterId;
    private Long advertiseId;

    public Comment(Long commentId, String content, Long commenterId) {
        this.commentId = commentId;
        this.content = content;
        this.commenterId = commenterId;
        this.advertiseId = null;
    }

}

