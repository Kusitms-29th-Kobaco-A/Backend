package core.kobaco.infra.jpa.advertisement.entity;

import core.kobaco.infra.jpa.BaseEntity;
import core.kobaco.infra.jpa.keyword.KeywordEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "advertisement_keyword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertisementKeywordEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private AdvertisementEntity advertisement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private KeywordEntity keyword;

    public static AdvertisementKeywordEntity of(Long id, AdvertisementEntity advertisement, KeywordEntity keyword) {
        return new AdvertisementKeywordEntity(id, advertisement, keyword);
    }
}