package core.kobaco.application.comment.controller;

import core.kobaco.application.comment.service.CommentLikeDetailResponse;
import core.kobaco.application.comment.service.CommentService;
import core.kobaco.application.comment.service.dto.CommentDetail;
import core.kobaco.global.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentDetail> createComment(@RequestBody CommentDetail commentDTO) {
        CommentDetail createdComment = commentService.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentDetail>> getAllComments() {
        List<CommentDetail> comments = commentService.getAllComments();
        return ApiResponse.success(comments);
    }

    @PatchMapping("/{commentId}/like")
    public void likeComment(@PathVariable Long commentId) {
        commentService.likeComment(commentId);
    }

    @GetMapping("/{commentId}/like")
    public CommentLikeDetailResponse getCommentLikeCount(@PathVariable Long commentId) {
        return commentService.getCommentLikeCount(commentId);
    }

}

