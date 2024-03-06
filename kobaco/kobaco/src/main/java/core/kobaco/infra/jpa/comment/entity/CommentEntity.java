package core.kobaco.infra.jpa.comment.entity;


import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
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

    @ManyToOne
    @JoinColumn(name = "advertise_id", nullable = false)
    private AdvertisementEntity advertise;

    private CommentEntity(Long id,String content, UserEntity user, AdvertisementEntity advertise) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.advertise = advertise;
    }

    public static CommentEntity of(Long id, String content, UserEntity user, AdvertisementEntity advertise) {
        return new CommentEntity(id, content, user, advertise);
    }

    public static CommentEntity from(Long id) {
        return new CommentEntity(id,null, null, null);
    }
}