package core.kobaco.infra.keyword;

import core.kobaco.infra.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "keyword")
public class KeywordEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    private String description;
    private KeywordEntity(Long keywordId, String description) {
        this.keywordId = keywordId;
        this.description = description;
    }
    public static KeywordEntity of(Long keywordId, String description) {
        return new KeywordEntity(null,description);
    }

    public static KeywordEntity from(Long keywordId){
        return new KeywordEntity(keywordId,null);
    }
}

