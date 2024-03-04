package core.kobaco.infra.comment;

import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity,Long> {

}