package core.kobaco.domain.file;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Namespace {
    private Long id;
    private Long userId;

    public static Namespace from(Long userId) {
        return new Namespace(null, userId);
    }

    public static Namespace of(Long namespaceId, Long userId) {
        return new Namespace(namespaceId, userId);
    }
}
