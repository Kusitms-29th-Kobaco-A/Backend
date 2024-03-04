package core.kobaco.domain.keyword.service;

import core.kobaco.domain.keyword.Keyword;
import core.kobaco.domain.keyword.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordReader {
    private final KeywordRepository keywordRepository;

    public Keyword getKeyword(Long keywordId) {
        return keywordRepository.findById(keywordId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid keywordId"));
    }

    public List<Keyword> getKeywordList(List<Long> keywordIdList) {
        return keywordRepository.findAllById(keywordIdList);
    }

}
