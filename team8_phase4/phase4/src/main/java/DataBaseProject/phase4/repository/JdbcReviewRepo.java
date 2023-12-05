package DataBaseProject.phase4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import DataBaseProject.phase4.domain.Review;
import DataBaseProject.phase4.domain.ShoppingCart;

public class JdbcReviewRepo {

    private static final String SQL = "select n.id, t1.review, t1.productId, t1.reviewDate from (select r.userid, r.review, r.productId, r.reviewDate from PurchaseTransaction pt, review r where pt.productId = r.productId and pt.userid = r.userid) t1 join NormalUser n on t1.userId = n.id";
    private static final String SQL2 = "insert into review values(?, ?, ?, ?, ?)";

    private static final String SQL3 = "delete from review where reviewId = ?";
    private final Connection conn;


    public JdbcReviewRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Review> getReviewInfos(){
        ArrayList<Review> reviews = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String userId = resultSet.getString(1);
                String review = resultSet.getString(2);
                Long productId = resultSet.getLong(3);
                Time purchaseTime = resultSet.getTime(4);
                reviews.add(new Review(null, userId, productId, review, purchaseTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public Status addReview(Review review){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setLong(1, review.getReviewId());
            preparedStatement.setString(2, review.getUserId());
            preparedStatement.setLong(3, review.getProductId());
            preparedStatement.setString(4, review.getReview());
            preparedStatement.setTime(5, review.getReviewDate());


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public Status deleteReview(Review review){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL3)) {
            preparedStatement.setLong(1, review.getReviewId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
}
