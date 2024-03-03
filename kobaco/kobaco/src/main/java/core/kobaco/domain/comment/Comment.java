package core.kobaco.domain.comment;
import core.kobaco.domain.user.User;
import core.kobaco.infra.jpa.user.UserEntity;
import lombok.*;

@Getter
@AllArgsConstructor
public class Comment {
    private Long commentId;
    private String content;
    private UserEntity commenter;

    public static Comment of (Long commentId, String content, UserEntity commenter) {
        return new Comment(commentId, content, commenter);
    }

}

