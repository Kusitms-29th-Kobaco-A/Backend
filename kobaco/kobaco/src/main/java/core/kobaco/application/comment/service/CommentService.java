package core.kobaco.application.comment.service;

import core.kobaco.application.comment.service.dto.CommentDetail;
import core.kobaco.domain.comment.*;

import core.kobaco.domain.user.UserUtils;

import core.kobaco.domain.user.service.UserReader;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserReader userReader;
    private final UserUtils userUtils;
    private final CommentLikeManager commentLikeManager;

    public CommentService(CommentRepository commentRepository, UserReader userReader, UserUtils userUtils, CommentLikeManager commentLikeManager) {
        this.commentRepository = commentRepository;
        this.userReader = userReader;
        this.userUtils = userUtils;
        this.commentLikeManager = commentLikeManager;
    }

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

        String userEmail = getUserEmail(userId);

        return new CommentDetail(
                savedCommentEntity.getContent());
    }

    public List<CommentDetail> getAllComments(Long advertiseId) {
        List<Comment> comments = commentRepository.findAllByAdvertiseId(advertiseId);
        return comments.stream()
                .map(comment -> new CommentDetail(comment.getContent()))
                .collect(Collectors.toList());
    }

    private String getUserEmail(Long userId) {
        return userReader.read(userId).getEmail();

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
