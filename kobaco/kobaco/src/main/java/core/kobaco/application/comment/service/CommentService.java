package core.kobaco.application.comment.service;

import core.kobaco.application.comment.service.dto.CommentDTO;
import core.kobaco.infra.comment.CommentEntity;
import core.kobaco.infra.comment.CommentJpaRepository;
import core.kobaco.infra.user.UserEntity;
import core.kobaco.infra.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentJpaRepository commentRepository;
    private final UserJpaRepository userRepository;

    @Autowired
    public CommentService(CommentJpaRepository commentRepository, UserJpaRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public CommentDTO createComment(String content, String userEmail) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        UserEntity user = userOptional.get();
        CommentEntity comment = CommentEntity.of(content, user);
        commentRepository.save(comment);
        return toDTO(comment);
    }

    public List<CommentDTO> getAllComments() {
        List<CommentEntity> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void likeComment(Long commentId, String userEmail) {
        Optional<CommentEntity> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("Comment not found");
        }

        CommentEntity comment = commentOptional.get();
        if (!comment.getLikedBy().contains(userEmail)) {
            comment.getLikedBy().add(userEmail);
            commentRepository.save(comment);
        }
    }

    public List<CommentDTO> getCommentsByUser(String userEmail) {
        List<CommentEntity> comments = commentRepository.findByUserEmail(userEmail);
        return comments.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private CommentDTO toDTO(CommentEntity comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserEmail(comment.getUser().getEmail());
        dto.setLikes(comment.getLikedBy().size());
        return dto;
    }
}
