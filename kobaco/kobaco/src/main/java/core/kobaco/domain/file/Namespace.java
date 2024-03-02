package core.kobaco.domain.file;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class Namespace {
    private Long namespaceId;
    private Long userId;
    private Namespace(Long namespaceId, Long userId) {
        this.namespaceId = namespaceId;
        this.userId = userId;
    }

    public static Namespace from(Long userId) {
        return new Namespace(null, userId);
    }

    public static Namespace of(Long namespaceId, Long userId) {
        return new Namespace(namespaceId, userId);
    }
}
