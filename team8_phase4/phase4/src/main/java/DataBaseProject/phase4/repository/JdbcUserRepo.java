package DataBaseProject.phase4.repository;

import DataBaseProject.phase4.domain.NormalUser;
import DataBaseProject.phase4.domain.Product;
import DataBaseProject.phase4.domain.Rank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserRepo {

    private static final String SQL1 = "delete from normaluser where id = ?";
    private static final String SQL2 = "delete from branduser where id = ?";

    private static final String SQL3 = "select * from normaluser where id = ?";

    private final Connection conn;


    public JdbcUserRepo(Connection conn) {
        this.conn = conn;
    }

    public Status deleteNormalUser(String userId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL1)){
            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public Status deleteBrandUser(String userId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
    String id;
    String passWord;
    String emailAddress;
    String phoneNumber;
    Rank rank;
    String name;
    Long age;
    String address;
    public NormalUser getUserInfo(String id){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL3)){
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String userId = resultSet.getString(1);
            String passWord = resultSet.getString(2);
            String emailAddress = resultSet.getString(3);
            String phoneNumber = resultSet.getString(4);
            String name = resultSet.getString(6);
            Long age = resultSet.getLong(7);
            String address = resultSet.getString(8);
            return new NormalUser(userId, passWord, emailAddress, phoneNumber, null, name, age, address);

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
