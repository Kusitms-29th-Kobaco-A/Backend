package core.kobaco.domain.advertise;

import io.jsonwebtoken.impl.security.PrivateECKey;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdvertisementDetail {
    private List<String> peopleList;
    private List<String> objectList;
    private String owner;
    private String ownerCompany;
    private String makerCompany;


    public static AdvertisementDetail of(List<String> peopleList,
                                         List<String> objectList,
                                         String owner,
                                         String ownerCompany,
                                         String makerCompany) {
        return new AdvertisementDetail(peopleList, objectList, owner, ownerCompany, makerCompany);
    }
}
