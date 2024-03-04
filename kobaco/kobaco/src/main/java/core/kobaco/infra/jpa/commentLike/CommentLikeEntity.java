package core.kobaco.infra.jpa.commentLike;

import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import core.kobaco.infra.jpa.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentEntity comment;


    private CommentLikeEntity(Long id, UserEntity user, CommentEntity comment) {
        this.id = id;
        this.user = user;
        this.comment = comment;
    }

    public static CommentLikeEntity of(Long id, UserEntity user, CommentEntity comment) {
        return new CommentLikeEntity(id, user, comment);
    }
}
