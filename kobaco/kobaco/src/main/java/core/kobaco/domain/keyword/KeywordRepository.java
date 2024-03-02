package core.kobaco.domain.keyword;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository {
    Optional<Keyword> findById(Long keywordId);
    Optional<Keyword> findByKeyword(String keyword);


    Keyword save(Keyword keyword);
}
