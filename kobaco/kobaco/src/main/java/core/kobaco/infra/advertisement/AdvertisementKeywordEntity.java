package core.kobaco.infra.advertisement;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "advertisement_keyword")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AdvertisementKeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private AdvertisementEntity advertisement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private KeywordEntity keyword;

}