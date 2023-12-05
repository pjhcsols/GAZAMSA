package DataBaseProject.phase4.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAdminRepo {

    private static final String SQL1 = "delete from branduser where id = ?";
    private static final String SQL2 = "delete from normaluser where id = ?";
    private final Connection conn;

    public JdbcAdminRepo(Connection conn) {
        this.conn = conn;
    }

    public Status deleteBrandUser(String brandUserId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL1)){
            preparedStatement.setString(1, brandUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.FAIL : Status.SUCCESS;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public Status deleteNormalUser(String normalUserId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL2)){
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
