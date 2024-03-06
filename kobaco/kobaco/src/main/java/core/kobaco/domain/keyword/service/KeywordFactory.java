package core.kobaco.domain.keyword.service;

import core.kobaco.domain.keyword.Keyword;
import core.kobaco.domain.keyword.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordFactory {
    private final KeywordRepository keywordRepository;

    public Long upsert(String keyword) {
        return keywordRepository.findByKeyword(keyword)
            .orElseGet(() -> keywordRepository.save(Keyword.from(keyword)))
            .getId();
    }

    public List<Long> upsert(List<String> keywords) {
        return keywords.stream().map(this::upsert).toList();
    }
}
