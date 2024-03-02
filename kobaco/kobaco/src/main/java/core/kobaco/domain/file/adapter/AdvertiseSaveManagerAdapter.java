package core.kobaco.domain.file.adapter;

import core.kobaco.domain.advertise.service.AdvertiseSaveManager;

public class AdvertiseSaveManagerAdapter implements AdvertiseSaveManager {
    @Override
    public <T> void save(T request) {

    }

    @Override
    public <T> boolean isSave(T request) {
        return false;
    }
}
