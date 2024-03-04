package core.kobaco.infra.jpa.comment.entity;

import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    private CommentEntity(String content, UserEntity user, AdvertisementEntity advertise) {
        this.content = content;
        this.user = user;
        this.advertise = advertise;
    }

    public static CommentEntity of(String content, UserEntity user, AdvertisementEntity advertise) {
        return new CommentEntity(content, user, advertise);
    }

    public static CommentEntity from(Long id) {
        return new CommentEntity(null, null, null);
    }
}