package DataBaseProject.phase4.service;

import java.util.ArrayList;
import DataBaseProject.phase4.domain.Review;
import DataBaseProject.phase4.repository.JdbcLikeInfosRepo;
import DataBaseProject.phase4.repository.JdbcReviewRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class ReviewService {

    private final JdbcReviewRepo jdbcReviewRepo;


    public ReviewService(JdbcReviewRepo jdbcReviewRepo) {
        this.jdbcReviewRepo = jdbcReviewRepo;
    }

    public Status addReview(Review review){
        return jdbcReviewRepo.addReview(review);
    }

    public Status deleteReview(Review review){
        return jdbcReviewRepo.deleteReview(review);
    }

    public ArrayList<Review> getReviewInfos(String userId){
        ArrayList<Review> ret = jdbcReviewRepo.getReviewInfos();
        ArrayList<Review> newRet = new ArrayList<>();

        for(Review item : ret){
            if (item.getUserId().equals(userId))newRet.add(item);
        }
        return newRet;
    }
}
