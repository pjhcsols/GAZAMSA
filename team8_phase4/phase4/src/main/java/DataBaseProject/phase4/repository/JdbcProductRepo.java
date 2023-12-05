package DataBaseProject.phase4.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DataBaseProject.phase4.domain.Product;

public class JdbcProductRepo {

    private static final String SQL1 = "insert into product values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL2 = "select p.productId, p.productName from product p join brandUser b on p.branduserid = b.id where b.id = ?";
    private static final String SQL3 = "delete from product where productId = ?";
    private static final String SQL4 = "select * from product";
    private static final String SQL5 = "select * from product where productId = ?";
    private static final String SQL6 = "select max(productId) from product";

    private final Connection conn;

    public JdbcProductRepo(Connection conn) {
        this.conn = conn;
    }

    public Status addProductInfo(Product product){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL1)) {
            preparedStatement.setLong(1, product.getProductId());
            preparedStatement.setLong(2, product.getProductCategoryId());
            preparedStatement.setString(3, product.getBrandUserId());
            preparedStatement.setString(4, product.getProductName());
            preparedStatement.setLong(5, product.getProductPrice());
            preparedStatement.setString(6, product.getProductDesc());
            preparedStatement.setString(7, product.getProductPhotoImage());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public ArrayList<Product> getProductsById(String brandUserId){
        ArrayList<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1,brandUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong(1);
                String productName = resultSet.getString(2);
                products.add(new Product(productId,null,null, productName, null, null));
            }
    } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public Status updateProductAttribute(String attributeName, String newValue, long productId) {
        String updateSql = "UPDATE product SET " + attributeName + " = ? WHERE productId = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(updateSql)) {
            preparedStatement.setString(1, newValue);
            preparedStatement.setLong(2, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return Status.SUCCESS;
            } else {
                return Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Status.FAIL;
    }
    public Status updateProductAttribute(String attributeName, Long newValue, long productId) {
        String updateSql = "UPDATE product SET " + attributeName + " = ? WHERE productId = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(updateSql)) {
            preparedStatement.setLong(1, newValue);
            preparedStatement.setLong(2, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return Status.SUCCESS;
            } else {
                return Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Status.FAIL;
    }

    public Status deleteProduct(Long productId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL3)){
            preparedStatement.setLong(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Status.FAIL;
    }

    public ArrayList<Product> getProductInfos(){
        ArrayList<Product> products = new ArrayList<>();
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL4)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Long productId = resultSet.getLong(1);
                Long productCategoryId = resultSet.getLong(2);
                String brandUserId = resultSet.getString(3);
                String productName = resultSet.getString(4);
                Long productPrice = resultSet.getLong(5);
                String productDesc = resultSet.getString(6);
                Product product = new Product(productId, productCategoryId, brandUserId, productName,productPrice, productDesc);
                products.add(product);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public Product getProductById(Long productId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL5)){
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            productId = resultSet.getLong(1);
            Long productCategoryId = resultSet.getLong(2);
            String brandUserId = resultSet.getString(3);
            String productName = resultSet.getString(4);
            Long productPrice = resultSet.getLong(5);
            String productDesc = resultSet.getString(6);
            String productPhotoImage = resultSet.getString(7);
            return new Product(productId, productCategoryId, brandUserId, productName,productPrice, productDesc, productPhotoImage);

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public long getLastId() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL6)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long ret = resultSet.getLong(1);
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
