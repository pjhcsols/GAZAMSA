package DataBaseProject.phase4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import DataBaseProject.phase4.domain.PaymentType;
import DataBaseProject.phase4.domain.PurchaseTransaction;

public class JdbcTransactionInfoRepo {

    private static final String SQL = "select * from purchasetransaction";
    private static final String SQL2 = "insert into purchasetransaction values(?, ?, ?, ?, ?, ?)";
    private final Connection conn;

    public JdbcTransactionInfoRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<PurchaseTransaction> getTransactionInfos() {
        ArrayList<PurchaseTransaction> transactionInfos = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Long transactionId = resultSet.getLong(1);
                String userId = resultSet.getString(2);
                Long productId = resultSet.getLong(3);
                Long totalPrice = resultSet.getLong(4);
                PaymentType paymentType = StringToPaymentType(resultSet.getString(5));
                Time purchaseTime = resultSet.getTime(6);
                transactionInfos.add(new PurchaseTransaction(transactionId, userId, productId, totalPrice, paymentType, purchaseTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionInfos;
    }
    public Status createTransaction(PurchaseTransaction purchaseTransaction){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setLong(1, purchaseTransaction.getTransactionId());
            preparedStatement.setString(2, purchaseTransaction.getUserId());
            preparedStatement.setLong(3, purchaseTransaction.getProductId());
            preparedStatement.setLong(4, purchaseTransaction.getTotalPrice());
            preparedStatement.setString(5, PaymentTypeToStringType(purchaseTransaction.getPaymentType()));
            preparedStatement.setTime(6, purchaseTransaction.getPurchaseTime());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
    private PaymentType StringToPaymentType(String num){
        if (num.equals("CASH"))return PaymentType.CASH;
        else if (num.equals("CARD"))return PaymentType.CARD;
        else if (num.equals("KAKAO"))return PaymentType.KAKAO;
        else return PaymentType.NAVER;
    }

    private String PaymentTypeToStringType(PaymentType num){
        if (num == PaymentType.CASH)return "CASH";
        else if (num == PaymentType.CARD)return "CARD";
        else if (num == PaymentType.KAKAO)return "KAKAO";
        else return "NAVER";
    }

}
