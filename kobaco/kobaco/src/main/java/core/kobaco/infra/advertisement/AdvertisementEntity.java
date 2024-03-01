package core.kobaco.infra.advertisement;

import core.kobaco.infra.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "advertisement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdvertisementEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String videoUrl;
    private String title;
    private String description;
    private String copy;
    private String copyDetail;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> peopleList;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> objectList;
    private String owner;
    private String ownerCompany;
    private String makerCompany;

    private AdvertisementEntity(Long id,
                                String videoUrl,
                                String title,
                                String description,
                                String copy,
                                String copyDetail,
                                List<String> peopleList,
                                List<String> objectList,
                                String owner,
                                String ownerCompany,
                                String makerCompany) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.title = title;
        this.description = description;
        this.copy = copy;
        this.copyDetail = copyDetail;
        this.peopleList = peopleList;
        this.objectList = objectList;
        this.owner = owner;
        this.ownerCompany = ownerCompany;
        this.makerCompany = makerCompany;
    }

    public static AdvertisementEntity of(
        Long id,
        String videoUrl,
        String title,
        String description,
        String copy,
        String copyDetail,
        List<String> peopleList,
        List<String> objectList,
        String owner,
        String ownerCompany,
        String makerCompany) {
        return new AdvertisementEntity(id, videoUrl, title, description, copy, copyDetail, peopleList, objectList, owner, ownerCompany, makerCompany);
    }

    public static AdvertisementEntity from(Long id){
        return new AdvertisementEntity(id, null, null, null, null, null, null, null, null, null, null);
    }
}


