package core.kobaco.infra.jpa.commentLike;


import core.kobaco.domain.commentLike.service.CommentLike;
import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import core.kobaco.infra.jpa.commentLike.CommentLikeEntity;
import core.kobaco.infra.jpa.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentLikeMapper {
    public CommentLikeEntity toEntity(CommentLike commentLike) {
        return CommentLikeEntity.of(
                commentLike.getLikeId(),
                UserEntity.from(commentLike.getUserId()),
                CommentEntity.from(commentLike.getCommentId())
        );
    }

    public CommentLike toDomain(CommentLikeEntity commentLikeEntity) {
        return CommentLike.of(
                commentLikeEntity.getId(),
                commentLikeEntity.getUser().getId(),
                commentLikeEntity.getComment().getId()
        );
    }
}
