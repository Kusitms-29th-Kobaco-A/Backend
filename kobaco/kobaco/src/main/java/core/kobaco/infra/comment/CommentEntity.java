package core.kobaco.infra.comment;

import core.kobaco.infra.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ElementCollection
    @CollectionTable(name = "comment_likes", joinColumns = @JoinColumn(name = "comment_id"))
    @Column(name = "user_email")
    private Set<String> likedBy = new HashSet<>();

    private CommentEntity(String content, UserEntity user) {
        this.content = content;
        this.user = user;
    }
    public static CommentEntity of(String content, UserEntity user) {
        return new CommentEntity(content, user);
    }
}
