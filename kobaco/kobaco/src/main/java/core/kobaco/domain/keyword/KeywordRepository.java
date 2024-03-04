package core.kobaco.domain.keyword;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository {
    Optional<Keyword> findById(Long keywordId);
    Optional<Keyword> findByKeyword(String keyword);

    List<Keyword> findAllById(List<Long> keywordIdList);


    Keyword save(Keyword keyword);
}
