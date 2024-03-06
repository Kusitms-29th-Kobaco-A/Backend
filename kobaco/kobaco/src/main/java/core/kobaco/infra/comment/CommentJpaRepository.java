package core.kobaco.infra.comment;

import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findAllByAdvertiseId(Long advertiseId);
}

