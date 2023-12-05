package DataBaseProject.phase4.domain;

import java.sql.Time;

public class PurchaseTransaction {
    Long transactionId;
    String userId;
    Long productId;
    Long totalPrice;
    PaymentType paymentType;
    Time purchaseTime;

    public PurchaseTransaction(Long transactionId, String userId, Long productId, Long totalPrice,
            PaymentType paymentType,
            Time purchaseTime) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
        this.purchaseTime = purchaseTime;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Time getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Time purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
