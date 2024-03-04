package core.kobaco.infra.jpa.advertisement.entity;

import core.kobaco.infra.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "ADVERTISE_TREND")
public class AdvertisementTrendEntity extends BaseEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdvertisementEntity bestAdvertise;

    public static AdvertisementTrendEntity from(AdvertisementEntity bestAdvertise) {
        return new AdvertisementTrendEntity(null, bestAdvertise);
    }

    public static AdvertisementTrendEntity of(Long id, AdvertisementEntity bestAdvertise) {
        return new AdvertisementTrendEntity(id, bestAdvertise);
    }
}
