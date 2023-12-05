package DataBaseProject.phase4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DataBaseProject.phase4.domain.LikeInfo;
import DataBaseProject.phase4.domain.Product;
import DataBaseProject.phase4.domain.ShoppingCart;

public class JdbcLikeInfosRepo {

    private static final String SQL = "select * from product p where p.productid in (select l.productid from normaluser n, like_info l where n.id = l.userid and p.productid = l.productid)";
    private static final String SQL4 = "select * from like_info l where l.userId = ?";
    private static final String SQL2 = "insert into like_info values(?, ?, ?)";

    private static final String SQL3 = "delete from like_info where id = ?";

    private static final String SQL5 = "select * from like_info";
    private final Connection conn;

    public JdbcLikeInfosRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<LikeInfo> getLikeInfos(String userId){
        ArrayList<LikeInfo> likeInfos = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL4)) {
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                userId = resultSet.getString(2);
                int productId = resultSet.getInt(3);
                likeInfos.add(new LikeInfo(id, userId, (long)productId));
            }
            return likeInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Status doLike(LikeInfo likeInfo){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setLong(1, likeInfo.getId());
            preparedStatement.setString(2, likeInfo.getUserId());
            preparedStatement.setLong(3, likeInfo.getProductId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public Status unDoLike(LikeInfo likeInfo){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL3)) {
            preparedStatement.setLong(1, likeInfo.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public ArrayList<LikeInfo> getAllLikeInfo(){
        ArrayList<LikeInfo> likeInfos = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL5)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String userId = resultSet.getString(2);
                int productId = resultSet.getInt(3);
                likeInfos.add(new LikeInfo(id, userId, (long)productId));
            }
            return likeInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
