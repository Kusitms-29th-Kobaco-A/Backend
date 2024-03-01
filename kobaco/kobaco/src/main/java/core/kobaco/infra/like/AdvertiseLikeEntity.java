package core.kobaco.infra.like;

import core.kobaco.infra.advertisement.AdvertisementEntity;
import core.kobaco.infra.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "advertise_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdvertiseLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private AdvertisementEntity advertisement;

    private AdvertiseLikeEntity(UserEntity user, AdvertisementEntity advertisement) {
        this.user = user;
        this.advertisement = advertisement;
    }
    public static AdvertiseLikeEntity of(UserEntity user, AdvertisementEntity advertisement) {
        return new AdvertiseLikeEntity(user, advertisement);
    }
}

