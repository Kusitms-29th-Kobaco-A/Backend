package core.kobaco.application.comment.service;

import javax.swing.text.StyledEditorKit;
public record CommentLikeDetailResponse (Boolean isLike,Long likeCount){
    public static CommentLikeDetailResponse of(Boolean isLike, Long likeCount){
        return new CommentLikeDetailResponse(isLike, likeCount);
    }
}


