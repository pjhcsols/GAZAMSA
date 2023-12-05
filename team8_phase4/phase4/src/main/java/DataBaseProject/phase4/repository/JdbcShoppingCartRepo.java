package DataBaseProject.phase4.repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import DataBaseProject.phase4.domain.NormalUser;
import DataBaseProject.phase4.domain.ShoppingCart;

public class JdbcShoppingCartRepo {

    private static final String SQL = "SELECT s.orderid, s.userid, s.productid, s.amount FROM shoppingcart s, normaluser n WHERE n.id = s.userid";
    private static final String SQL2 = "insert into ShoppingCart values (?, ?, ?, ?)";
    private static final String SQL3 = "SELECT * FROM shoppingCart WHERE userId = ?";
    private static final String SQL4 = "DELETE FROM shoppingCart WHERE userId = ?";


    private final Connection conn;

    public JdbcShoppingCartRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<ShoppingCart> getShoppingCartInfos() {
        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String orderId = resultSet.getString(1);
                String userId = resultSet.getString(2);
                Long productId = resultSet.getLong(3);
                Long amount = resultSet.getLong(4);
                shoppingCarts.add(new ShoppingCart(orderId, userId, productId, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoppingCarts;
    }

    public Status addProductInShoppingCart(String normalUserId, Long productId, Long cnt){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1, LocalDateTime.now().toString() + normalUserId + productId.toString());
            preparedStatement.setString(2, normalUserId);
            preparedStatement.setLong(3, productId);
            preparedStatement.setLong(4, cnt);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public ArrayList<ShoppingCart> buyProductsInShoppingCart(String normalUserId) {
        ArrayList<ShoppingCart> shoppingCart = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL3)) {
            preparedStatement.setString(1, normalUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String orderId = resultSet.getString(1);
                String userId = resultSet.getString(2);
                Long productId = resultSet.getLong(3);
                Long amount = resultSet.getLong(4);
                shoppingCart.add(new ShoppingCart(orderId, userId, productId, amount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        deleteShoppingCart(normalUserId);

        return shoppingCart;
    }

    public Status deleteShoppingCart(String userId){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL4)) {
            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            return Status.FAIL;
        }
    }
}
