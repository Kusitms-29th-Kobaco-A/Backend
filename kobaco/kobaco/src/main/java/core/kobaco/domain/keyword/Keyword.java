package core.kobaco.domain.keyword;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Keyword {
    private Long id;
    private String keyword;

    public static Keyword of(Long keywordId, String keyword) {
        return new Keyword(keywordId, keyword);
    }

    public static Keyword from(String keyword) {
        return new Keyword(null, keyword);
    }
}
