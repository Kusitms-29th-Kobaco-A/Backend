package core.kobaco.application.comment.controller;

import core.kobaco.application.comment.service.CommentLikeDetailResponse;
import core.kobaco.application.comment.service.CommentService;
import core.kobaco.application.comment.service.dto.CommentDetail;
import core.kobaco.global.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    @Operation(summary = "댓글 생성")
    @PostMapping("/{advertiseId}")
    public ResponseEntity<CommentDetail> createComment(@RequestBody CommentDetail commentDTO, @PathVariable Long advertiseId) {
        CommentDetail createdComment = commentService.createComment(commentDTO, advertiseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @Operation(summary = "댓글 조회")
    @GetMapping("/{advertiseId}")
    public ResponseEntity<List<CommentDetail>> getAllComments(@PathVariable Long advertiseId) {
        List<CommentDetail> comments = commentService.getAllComments(advertiseId);
        return ApiResponse.success(comments);
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

