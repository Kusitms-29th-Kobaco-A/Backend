package core.kobaco.application.comment.service;

import core.kobaco.application.comment.service.dto.CommentDetail;
import core.kobaco.domain.comment.*;

import core.kobaco.domain.user.User;
import core.kobaco.domain.user.UserRepository;
import core.kobaco.domain.user.UserUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final UserUtils userUtils;
    private final CommentLikeManager commentLikeManager;

    @Transactional
    public CommentDetail createComment(CommentDetail commentDetail, Long advertiseId) {

        final Long userId = userUtils.getRequestUserId();

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자가 인증되지 않았습니다.");
        }

        Comment comment = new Comment(
                null,
                commentDetail.getContent(),
                userId,
                advertiseId
        );

        Comment savedCommentEntity = commentRepository.save(comment, advertiseId);

        return new CommentDetail(
                savedCommentEntity.getCommentId(),
                savedCommentEntity.getContent(),
                getUserEmail(userId)
        );

    }

    public List<CommentDetail> getAllComments(Long advertiseId) {
        List<Comment> comments = commentRepository.findAllByAdvertiseId(advertiseId);
        return comments.stream()
                .map(comment -> new CommentDetail(comment.getCommentId(), comment.getContent(), getUserEmail(comment.getCommenterId())))
                .collect(Collectors.toList());
    }

    private String getUserEmail(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        return user.getEmail();
    }

    @Transactional
    public void likeComment(Long commentId) {
        final Long userId = userUtils.getRequestUserId();
        commentLikeManager.like(commentId, userId);
    }
    public CommentLikeDetailResponse getCommentLikeCount(final Long commentId) {
        return CommentLikeDetailResponse.of(
                commentLikeManager.isLike(commentId),
                commentLikeManager.getLikeCount(commentId)
        );
    }
}
