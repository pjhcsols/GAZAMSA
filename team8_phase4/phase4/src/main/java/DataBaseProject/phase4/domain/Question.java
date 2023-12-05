package DataBaseProject.phase4.domain;

import java.sql.Time;

public class Question {
    Long questionId;
    String userId;
    Long productId;
    String question;
    Time uploadDate;

    public Question(Long questionId, String userId, Long productId, String question, Time uploadDate) {
        this.questionId = questionId;
        this.userId = userId;
        this.productId = productId;
        this.question = question;
        this.uploadDate = uploadDate;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Time getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Time uploadDate) {
        this.uploadDate = uploadDate;
    }
}
