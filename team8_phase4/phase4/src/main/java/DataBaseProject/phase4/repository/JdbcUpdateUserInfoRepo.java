package DataBaseProject.phase4.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataBaseProject.phase4.domain.NormalUser;
import DataBaseProject.phase4.domain.Rank;

public class JdbcUpdateUserInfoRepo {

    private static final String SQL2 = "select * from normaluser u where u.id = ?";

    private final  Connection conn;


    public JdbcUpdateUserInfoRepo(Connection conn) {
        this.conn = conn;
    }

    public NormalUser getUserInfo(String normalUserId){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1, normalUserId);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    return null;
                }
                else{
                    String userId = resultSet.getString(1);
                    String password = resultSet.getString(2);
                    String emailAddress = resultSet.getString(3);
                    String phoneNumber = resultSet.getString(4);
                    int rank = resultSet.getInt(5);
                    String name = resultSet.getString(6);
                    Long age = resultSet.getLong(7);
                    String address = resultSet.getString(8);
                    return new NormalUser(userId, password, emailAddress, phoneNumber, intToRank(rank), name, age, address);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Status updateUserInfo(String attributeName, String attribute, String normalUserId){
        String SQL1 = "update NormalUser u set u." + attributeName + " = ? where u.id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL1)) {
            preparedStatement.setString(1, attribute);
            preparedStatement.setString(2, normalUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public Status updateUserInfo(String attributeName, Long attribute, String normalUserId){
        String SQL1 = "update NormalUser u set u." + attributeName + " = ? where u.id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL1)) {
            preparedStatement.setLong(1, attribute);
            preparedStatement.setString(2, normalUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }


    private Rank intToRank(int num){
        if (num == 0){
            return Rank.NORMAL_BRONZE;
        }
        else if (num == 1) {
            return Rank.NORMAL_SILVER;
        }
        return Rank.NORMAL_GOLD;
    }

}

