package core.kobaco.application.comment.controller;

import core.kobaco.application.comment.service.dto.CommentDTO;
import core.kobaco.application.comment.service.CommentService;
import core.kobaco.global.ApiResponse;
import core.kobaco.infra.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
                                                    Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ApiResponse.unauthorized(null);
        }

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        CommentDTO createdComment = commentService.createComment(commentDTO.getContent(), userEntity.getEmail());
        return ApiResponse.created(createdComment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return ApiResponse.success(comments);
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<Void> likeComment(@PathVariable Long commentId,
                                            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ApiResponse.unauthorized(null);
        }

        // 현재 인증된 사용자 정보를 가져옴
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        commentService.likeComment(commentId, userEntity.getEmail());
        return ApiResponse.success(null);
    }
}
