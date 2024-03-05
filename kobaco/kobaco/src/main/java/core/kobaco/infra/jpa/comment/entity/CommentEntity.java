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

    @Column(name = "advertise_id")
    private Long advertiseId;

    private CommentEntity(String content, UserEntity user, Long advertiseId) {
        this.content = content;
        this.user = user;
        this.advertiseId = advertiseId;
    }

    public static CommentEntity of(String content, UserEntity user) {
        return new CommentEntity(content, user, null);
    }

    public static CommentEntity from(Long id) {
        return new CommentEntity(null, null, null);
    }
}