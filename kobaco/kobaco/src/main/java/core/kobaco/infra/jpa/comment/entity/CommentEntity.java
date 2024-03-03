package core.kobaco.infra.jpa.comment.entity;

import core.kobaco.infra.jpa.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private CommentEntity(String content, UserEntity user) {
        this.content = content;
        this.user = user;
    }
    public static CommentEntity of(String content, UserEntity user){
        return new CommentEntity(content, user);
    }
    public static CommentEntity from(Long id){
        return new CommentEntity(null, null);
    }
}
