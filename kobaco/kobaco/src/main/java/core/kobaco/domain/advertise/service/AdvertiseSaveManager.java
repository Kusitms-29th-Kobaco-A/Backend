package core.kobaco.domain.advertise.service;

public interface AdvertiseSaveManager{
    <T> void save(T request);
    <T> boolean isSave(T request);
}
