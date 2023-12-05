package DataBaseProject.phase4.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcLoginRepo {

    private static final String SQL1 = "SELECT * FROM normaluser u WHERE u.id = ? AND u.passWord = ?";
    private static final String SQL2 = "SELECT * FROM branduser u WHERE u.id = ? AND u.passWord = ?";
    private static final String SQL3 = "SELECT * FROM admin u WHERE u.id = ? AND u.passWord = ?";
    private final Connection conn;

    public JdbcLoginRepo(Connection conn) {
        this.conn = conn;
    }

    public Status normalUserLogin(String normalUserId, String passWord) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL1)) {
            preparedStatement.setString(1, normalUserId);
            preparedStatement.setString(2, passWord);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public Status brandUserLogin(String brandUserId, String passWord) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1, brandUserId);
            preparedStatement.setString(2, passWord);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
    public Status adminLogin(String adminId, String passWord) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL3)) {
            preparedStatement.setString(1, adminId);
            preparedStatement.setString(2, passWord);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
}
