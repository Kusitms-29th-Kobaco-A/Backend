package core.kobaco.infra.jpa.like;

import core.kobaco.infra.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.user.UserEntity;
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


    private AdvertiseLikeEntity(Long id, UserEntity user, AdvertisementEntity advertisement) {
        this.id = id;
        this.user = user;
        this.advertisement = advertisement;
    }

    public static AdvertiseLikeEntity of(Long id, UserEntity user, AdvertisementEntity advertisement) {
        return new AdvertiseLikeEntity(id, user, advertisement);
    }
}

