package core.kobaco.infra.jpa.keyword;

import core.kobaco.domain.keyword.Keyword;
import org.springframework.stereotype.Component;

@Component
public class KeywordMapper {
    public KeywordEntity toEntity(Keyword keyword) {
        return KeywordEntity.of(keyword.getKeywordId(), keyword.getKeyword());
    }

    public Keyword toDomain(KeywordEntity keywordEntity) {
        return Keyword.of(keywordEntity.getKeywordId(), keywordEntity.getDescription());
    }
}
