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
    private Long namespaceId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


}
