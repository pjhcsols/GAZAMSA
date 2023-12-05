package DataBaseProject.phase4.domain;

import java.sql.Time;

public class ReviewComment {
    Long reviewCommentId;
    Long reviewId;
    String reviewComment;
    Time commentDate;

    public ReviewComment(Long reviewCommentId, Long reviewId, String reviewComment, Time commentDate) {
        this.reviewCommentId = reviewCommentId;
        this.reviewId = reviewId;
        this.reviewComment = reviewComment;
        this.commentDate = commentDate;
    }

    public Long getReviewCommentId() {
        return reviewCommentId;
    }

    public void setReviewCommentId(Long reviewCommentId) {
        this.reviewCommentId = reviewCommentId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Time getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Time commentDate) {
        this.commentDate = commentDate;
    }
}
