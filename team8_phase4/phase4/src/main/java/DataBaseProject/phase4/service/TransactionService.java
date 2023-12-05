package DataBaseProject.phase4.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import DataBaseProject.phase4.domain.PurchaseTransaction;
import DataBaseProject.phase4.repository.JdbcTransactionInfoRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


public class TransactionService {
    private final JdbcTransactionInfoRepo jdbcTransactionInfoRepo;

    public TransactionService(JdbcTransactionInfoRepo jdbcTransactionInfoRepo) {
        this.jdbcTransactionInfoRepo = jdbcTransactionInfoRepo;
    }

    public Status recordTransaction(PurchaseTransaction purchaseTransaction){
        return jdbcTransactionInfoRepo.createTransaction(purchaseTransaction);
    }

    public ArrayList<PurchaseTransaction> getTransactionInfos(String userId){

        ArrayList<PurchaseTransaction> ret = jdbcTransactionInfoRepo.getTransactionInfos();
        ArrayList<PurchaseTransaction> newRet = new ArrayList<>();
        for(PurchaseTransaction item : ret){
            if(item.getUserId() != null && item.getUserId().equals(userId))newRet.add(item);
        }
        return newRet;
    }
}
