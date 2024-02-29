package core.kobaco.infra.advertisement;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "advertisement")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class AdvertisementEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String videoUrl;
        private String title;
        private String description;
        private LocalDateTime createdAt;
        private String copy;
        private String copyDetail;

        private AdvertisementEntity(String videoUrl, String title, String description,
                                    LocalDateTime createdAt, String copy,String copyDetail) {
            this.videoUrl = videoUrl;
            this.title = title;
            this.description = description;
            this.createdAt = createdAt;
            this.copy = copy;
            this.copyDetail = copyDetail;
        }

        public static AdvertisementEntity of(String videoUrl, String title, String description,
                                             LocalDateTime createdAt, String copy,String copyDetail) {
            return new AdvertisementEntity(videoUrl, title, description, createdAt, copy, copyDetail);
        }
    }


