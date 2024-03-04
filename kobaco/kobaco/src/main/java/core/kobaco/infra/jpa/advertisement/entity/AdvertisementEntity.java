package core.kobaco.infra.jpa.advertisement.entity;

import core.kobaco.infra.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "advertisement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertisementEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String videoUrl;
    private String title;
    private LocalDate uploadDate;
    private Time videoTime;
    private String copy;
    private String copyDetail;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> peopleList;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> objectList;
    private String owner;
    private String ownerCompany;
    private String makerCompany;

    public static AdvertisementEntity of(
        Long id,
        String videoUrl,
        Time videoTime,
        String title,
        LocalDate uploadDate,
        String copy,
        String copyDetail,
        List<String> peopleList,
        List<String> objectList,
        String owner,
        String ownerCompany,
        String makerCompany) {
        return new AdvertisementEntity(id, videoUrl, title, uploadDate, videoTime, copy, copyDetail, peopleList, objectList, owner, ownerCompany, makerCompany);
    }

    public static AdvertisementEntity from(Long id){
        return new AdvertisementEntity(id, null, null, null, null, null, null, null, null, null, null, null);
    }
}


