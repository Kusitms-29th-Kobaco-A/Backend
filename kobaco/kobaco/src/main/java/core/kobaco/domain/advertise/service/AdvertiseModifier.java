package core.kobaco.domain.advertise.service;

import core.kobaco.domain.advertise.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AdvertiseModifier {
    private final AdvertisementRepository advertisementRepository;
    private final TransactionTemplate transactionTemplate;


    public void upViewCount(Long advertiseId) {
        CompletableFuture.supplyAsync(() ->{
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    advertisementRepository.upViewCount(advertiseId);
                }
            });
            return null;
        });
    }
}
