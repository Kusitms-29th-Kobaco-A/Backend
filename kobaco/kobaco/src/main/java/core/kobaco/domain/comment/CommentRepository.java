package core.kobaco.domain.comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(Long commentId);
    Comment save(Comment comment);
    List<Comment> findAll();

}
