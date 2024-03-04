package core.kobaco.infra.jpa.commentLike;


import core.kobaco.infra.jpa.commentLike.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CommentLikeJpaRepository extends JpaRepository<CommentLikeEntity, Long> {
    Long countByCommentId(Long commentId);

    Boolean existsByCommentIdAndUserId(Long commentId, Long userId);

    Optional<CommentLikeEntity> findByCommentIdAndUserId(Long commentId, Long userId);
}
