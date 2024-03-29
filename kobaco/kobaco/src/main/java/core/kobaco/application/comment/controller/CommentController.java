package core.kobaco.application.comment.controller;

import core.kobaco.application.comment.service.CommentLikeDetailResponse;
import core.kobaco.application.comment.service.CommentService;
import core.kobaco.application.comment.service.dto.CommentCreateRequest;
import core.kobaco.application.comment.service.dto.CommentDetailResponse;
import core.kobaco.global.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    @Operation(summary = "댓글 생성")
    @PostMapping("/{advertiseId}")
    public ResponseEntity<CommentCreateRequest> createComment(@RequestBody CommentCreateRequest commentDTO, @PathVariable Long advertiseId) {
        CommentCreateRequest createdComment = commentService.createComment(commentDTO.getContent(), advertiseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }


    @Operation(summary = "댓글 조회")
    @GetMapping("/{advertiseId}")
    public ResponseEntity<Page<CommentDetailResponse>> getAllComments(@PathVariable Long advertiseId, Pageable pageable) {
        Page<CommentDetailResponse> commentsPage = commentService.getAllComments(advertiseId, pageable);
        return ApiResponse.success(commentsPage);
    }

    @Operation(summary = "댓글 좋아요")
    @PatchMapping("/{commentId}/like")
    public void likeComment(@PathVariable Long commentId) {
        commentService.likeComment(commentId);
    }

    @Operation(summary = "댓글 좋아요 수")
    @GetMapping("/{commentId}/like")
    public CommentLikeDetailResponse getCommentLikeCount(@PathVariable Long commentId) {
        return commentService.getCommentLikeCount(commentId);
    }

}


