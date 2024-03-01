package core.kobaco.infra.file.entity;

import core.kobaco.infra.BaseEntity;
import core.kobaco.infra.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "namespace")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NamespaceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "namespace_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private NamespaceEntity(Long id, UserEntity user) {
        this.id = id;
        this.user = user;
    }

    public static NamespaceEntity of(Long namespaceId, UserEntity user) {
        return new NamespaceEntity(namespaceId, user);
    }

    public static NamespaceEntity from(Long namespaceId) {
        return new NamespaceEntity(namespaceId, null);
    }
}
