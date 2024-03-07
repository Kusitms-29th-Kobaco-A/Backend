package core.kobaco.infra.comment;

import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {
    Page<CommentEntity> findAllByAdvertiseId(Long advertiseId, Pageable pageable);
}


