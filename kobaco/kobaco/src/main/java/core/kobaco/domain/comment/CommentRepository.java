package core.kobaco.domain.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(Long commentId);
    Comment save(Comment comment, Long advertiseId);
    Page<Comment> findAllByAdvertiseId(Long advertiseId, Pageable pageable);

}

