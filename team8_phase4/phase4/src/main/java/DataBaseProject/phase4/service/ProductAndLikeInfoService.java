package DataBaseProject.phase4.service;

import DataBaseProject.phase4.domain.Product;
import DataBaseProject.phase4.repository.JdbcProductRepo;
import java.time.LocalDate;
import java.util.ArrayList;
import DataBaseProject.phase4.domain.LikeInfo;
import DataBaseProject.phase4.repository.JdbcLikeInfosRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;



public class ProductAndLikeInfoService {

    private final JdbcLikeInfosRepo jdbcLikeInfosRepo;
    private final JdbcProductRepo jdbcProductRepo;


    public ProductAndLikeInfoService(JdbcLikeInfosRepo jdbcLikeInfosRepo, JdbcProductRepo jdbcProductRepo) {
        this.jdbcLikeInfosRepo = jdbcLikeInfosRepo;
        this.jdbcProductRepo = jdbcProductRepo;
    }

    public Status doLike(String userId, Long productId){
        LikeInfo likeInfo = new LikeInfo(Long.valueOf((LocalDate.now().toString() + userId + productId.toString()).hashCode()), userId, productId);
        return jdbcLikeInfosRepo.doLike(likeInfo);
    }

    public Status unDoLike(LikeInfo likeInfo){
        return jdbcLikeInfosRepo.unDoLike(likeInfo);
    }

    public ArrayList<LikeInfo> getLikeInfos(String userId){
        ArrayList<LikeInfo> ret = jdbcLikeInfosRepo.getLikeInfos(userId);

        return ret;
    }

    public Product getProductInfo(Long productId){
        return jdbcProductRepo.getProductById(productId);
    }

    public ArrayList<Product> getTop10Product(){
        ArrayList<LikeInfo> likes = jdbcLikeInfosRepo.getAllLikeInfo();
        ArrayList<Long> arr = new ArrayList<>(5000);
        for (int i = 0; i < 5000; i++){
            arr.add(0L);
        }

        for(LikeInfo like : likes){
            arr.set(like.getProductId().intValue(), arr.get(like.getProductId().intValue()) + 1);
        }
        ArrayList<Integer> sortedKeys = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            int maxidx = -1;
            Long maxval = 0L;
            for(int j = 0; j < 5000; j++){
                if (arr.get(j) > maxval){
                    maxval = arr.get(j);
                    maxidx = j;
                }
            }
            if (maxval != 0) {
                sortedKeys.add(maxidx);
                arr.set(maxidx, 0L);
            }
        }

        for (int i = 0; i < 10; i++){
            System.out.println(sortedKeys.get(i));
        }

        ArrayList<Product> products = new ArrayList<>();
        for(Integer productId : sortedKeys){
            products.add(jdbcProductRepo.getProductById(Long.valueOf(productId)));
        }

        return products;
    }
}
