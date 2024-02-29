package core.kobaco.infra.user.like;

import core.kobaco.infra.advertisement.AdvertisementEntity;
import core.kobaco.infra.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private AdvertisementEntity advertisement;

    private UserLikeEntity(UserEntity user, AdvertisementEntity advertisement) {
        this.user = user;
        this.advertisement = advertisement;
    }
    public static UserLikeEntity of(UserEntity user, AdvertisementEntity advertisement) {
        return new UserLikeEntity(user, advertisement);
    }
}

