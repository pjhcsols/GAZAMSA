package DataBaseProject.phase4.domain;

import java.sql.Time;

public class Answer {

    Long answerId;

    Long questionId;
    String answer;
    Time uploadDate;

    public Answer(Long answerId, Long questionId, String answer, Time uploadDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answer = answer;
        this.uploadDate = uploadDate;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Time getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Time uploadDate) {
        this.uploadDate = uploadDate;
    }
}
