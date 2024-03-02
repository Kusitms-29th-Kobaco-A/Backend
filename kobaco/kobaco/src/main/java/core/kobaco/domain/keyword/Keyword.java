package core.kobaco.domain.keyword;

import lombok.Getter;

@Getter
public class Keyword {
    private Long keywordId;
    private String keyword;

    private Keyword(Long keywordId, String keyword) {
        this.keywordId = keywordId;
        this.keyword = keyword;
    }

    public static Keyword of(Long keywordId, String keyword) {
        return new Keyword(keywordId, keyword);
    }

    public static Keyword from(String keyword) {
        return new Keyword(null, keyword);
    }
}
