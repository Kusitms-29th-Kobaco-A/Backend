package core.kobaco.infra.jpa.keyword;

import core.kobaco.domain.keyword.Keyword;
import core.kobaco.domain.keyword.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class KeywordRepositoryImpl implements KeywordRepository {
    private final KeywordJpaRepository keywordJpaRepository;
    private final KeywordMapper keywordMapper;
    @Override
    public Optional<Keyword> findById(Long keywordId) {
        return keywordJpaRepository.findById(keywordId)
            .map(keywordMapper::toDomain);
    }

    @Override
    public Optional<Keyword> findByKeyword(String keyword) {
        return keywordJpaRepository.findByDescription(keyword)
            .map(keywordMapper::toDomain)
            .map(findKeyword -> {
                log.info("findKeyword: {}", findKeyword.getKeyword());
                return findKeyword;
            });
    }

    @Override
    public List<Keyword> findAllById(List<Long> keywordIdList) {
        return keywordJpaRepository.findAllById(keywordIdList)
            .stream()
            .map(keywordMapper::toDomain)
            .toList();
    }

    @Override
    public Keyword save(Keyword keyword) {
        log.info("keyword: {}", keyword);
        return keywordMapper.toDomain(keywordJpaRepository.save(keywordMapper.toEntity(keyword)));
    }
}
