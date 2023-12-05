package DataBaseProject.phase4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataBaseProject.phase4.domain.BrandUser;
import DataBaseProject.phase4.domain.NormalUser;

public class JdbcJoinRepo {
    private final Connection conn;
    private static final String SQL1 = "select * from normaluser u where u.id = ?";
    private static final String SQL0 = "select * from branduser u where u.id = ?";
    private static final String SQL2 = "insert into NormalUser values(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL3 = "insert into BrandUser values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



    public JdbcJoinRepo(Connection conn) {
        this.conn = conn;
    }
    public Status checkDuplicate(String normalUserId, int type){
       if (type == 1){
           try(PreparedStatement preparedStatement = conn.prepareStatement(SQL1)){
               preparedStatement.setString(1, normalUserId);

               try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   return resultSet.next() ? Status.FAIL : Status.SUCCESS;
               }
           }catch (SQLException e) {
               e.printStackTrace();
               return Status.FAIL;
           }
       }
       else {
           try(PreparedStatement preparedStatement = conn.prepareStatement(SQL0)){
               preparedStatement.setString(1, normalUserId);

               try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   return resultSet.next() ? Status.FAIL : Status.SUCCESS;
               }
           }catch (SQLException e) {
               e.printStackTrace();
               return Status.FAIL;
           }
       }
    }
    public Status join(NormalUser normalUser){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1, normalUser.getId());
            preparedStatement.setString(2, normalUser.getPassWord());
            preparedStatement.setString(3, normalUser.getEmailAddress());
            preparedStatement.setString(4, normalUser.getPhoneNumber());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, normalUser.getName());
            preparedStatement.setInt(7, normalUser.getAge().intValue());
            preparedStatement.setString(8, normalUser.getAddress());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
    public Status join(BrandUser brandUser){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL3)) {
            preparedStatement.setString(1, brandUser.getId());
            preparedStatement.setString(2, brandUser.getPassWord());
            preparedStatement.setString(3, brandUser.getEmailAddress());
            preparedStatement.setString(4, brandUser.getPhoneNumber());
            preparedStatement.setInt(5, 4);
            preparedStatement.setString(6, brandUser.getFirmName());
            preparedStatement.setString(7, brandUser.getFirmPhoneNumber());
            preparedStatement.setString(8, brandUser.getFirmAddress());
            preparedStatement.setString(9, brandUser.getBusinessRegistration());
            preparedStatement.setString(10, brandUser.getFirmAddress());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
}
