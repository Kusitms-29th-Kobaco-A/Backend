package core.kobaco.infra.advertisement.entity;

import core.kobaco.infra.BaseEntity;
import core.kobaco.infra.keyword.KeywordEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "advertisement_keyword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    private AdvertisementKeywordEntity(Long id, AdvertisementEntity advertisement, KeywordEntity keyword) {
        this.id = id;
        this.advertisement = advertisement;
        this.keyword = keyword;
    }

    public static AdvertisementKeywordEntity of(Long id, AdvertisementEntity advertisement, KeywordEntity keyword) {
        return new AdvertisementKeywordEntity(id, advertisement, keyword);
    }
}