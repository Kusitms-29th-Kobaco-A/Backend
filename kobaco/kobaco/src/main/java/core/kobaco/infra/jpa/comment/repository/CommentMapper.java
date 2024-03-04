package core.kobaco.infra.jpa.comment.repository;

import core.kobaco.domain.comment.Comment;

import core.kobaco.infra.jpa.advertisement.entity.AdvertisementEntity;
import core.kobaco.infra.jpa.comment.entity.CommentEntity;
import core.kobaco.infra.jpa.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    public CommentEntity toEntity(Comment comment, Long userId, Long advertiseId) {
        UserEntity userEntity = UserEntity.from(userId);
        AdvertisementEntity advertisementEntity = AdvertisementEntity.from(advertiseId);
        return CommentEntity.of(comment.getContent(), userEntity, advertisementEntity);
    }
    public Comment toDomain(CommentEntity commentEntity) {
        return new Comment(
                commentEntity.getId(),
                commentEntity.getContent(),
                commentEntity.getUser().getId()
        );
    }

    public List<Comment> toDomainList(List<CommentEntity> commentEntities) {
        return commentEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
