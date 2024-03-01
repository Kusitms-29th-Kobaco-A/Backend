package core.kobaco.infra.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByUserEmail(String userEmail);
}
