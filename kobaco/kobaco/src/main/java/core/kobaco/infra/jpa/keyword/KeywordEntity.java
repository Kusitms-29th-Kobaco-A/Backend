package core.kobaco.infra.jpa.keyword;

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
@Table(name = "keyword")
public class KeywordEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    private String description;

    public static KeywordEntity of(Long keywordId, String description) {
        return new KeywordEntity(null,description);
    }

    public static KeywordEntity from(Long keywordId){
        return new KeywordEntity(keywordId,null);
    }
}

