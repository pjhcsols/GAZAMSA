package DataBaseProject.phase4.domain;

import java.sql.Time;

public class Review {
    Long reviewId;
    String userId;
    Long productId;
    String review;
    Time reviewDate;

    public Review(Long reviewId, String userId, Long productId, String review, Time reviewDate) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.productId = productId;
        this.review = review;
        this.reviewDate = reviewDate;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Time getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Time reviewDate) {
        this.reviewDate = reviewDate;
    }
}
