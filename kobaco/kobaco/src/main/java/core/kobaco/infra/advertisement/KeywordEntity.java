package core.kobaco.infra.advertisement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "keyword")
public class KeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    private String description;
    private KeywordEntity(String description) {
        this.description = description;
    }
    public static core.kobaco.infra.advertisement.KeywordEntity of(String description) {

        return new core.kobaco.infra.advertisement.KeywordEntity(description);
    }
}

