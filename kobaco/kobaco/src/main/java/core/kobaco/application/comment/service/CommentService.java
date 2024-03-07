package core.kobaco.application.comment.service;

import core.kobaco.application.comment.service.dto.CommentCreateRequest;
import core.kobaco.application.comment.service.dto.CommentDetailResponse;
import core.kobaco.domain.comment.*;

import core.kobaco.domain.commentlike.service.CommentLikeManager;
import core.kobaco.domain.user.UserUtils;

import core.kobaco.domain.user.service.UserReader;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


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
    public CommentCreateRequest createComment(String content, Long advertiseId) {
            final Long userId = userUtils.getRequestUserId();
            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자가 인증되지 않았습니다.");
            }
            Comment comment = new Comment(null, content, userId);
            Comment savedCommentEntity = commentRepository.save(comment, advertiseId);
            return CommentCreateRequest.of(savedCommentEntity.getContent());
        }

    public Page<CommentDetailResponse> getAllComments(Long advertiseId, Pageable pageable) {
        Page<Comment> commentsPage = commentRepository.findAllByAdvertiseId(advertiseId, pageable);
        return commentsPage.map(comment -> CommentDetailResponse.of(comment.getCommentId(), comment.getContent(), getUserEmail(comment.getCommenterId())));
    }

        private String getUserEmail(Long userId) {
            return userReader.read(userId).getEmail();
        }

        @Transactional
        public void likeComment(Long commentId) {
            final Long userId = userUtils.getRequestUserId();
            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자가 인증되지 않았습니다.");
            }
            commentLikeManager.like(commentId, userId);
        }
        public CommentLikeDetailResponse getCommentLikeCount(final Long commentId) {
            return CommentLikeDetailResponse.of(
                    commentLikeManager.isLike(commentId),
                    commentLikeManager.getLikeCount(commentId)
        );
    }
}

