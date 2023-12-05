package DataBaseProject.phase4;

import DataBaseProject.phase4.repository.JdbcJoinRepo;
import DataBaseProject.phase4.repository.JdbcLikeInfosRepo;
import DataBaseProject.phase4.repository.JdbcLoginRepo;
import DataBaseProject.phase4.repository.JdbcProductRepo;
import DataBaseProject.phase4.repository.JdbcReviewRepo;
import DataBaseProject.phase4.repository.JdbcShoppingCartRepo;
import DataBaseProject.phase4.repository.JdbcTransactionInfoRepo;
import DataBaseProject.phase4.repository.JdbcUpdateUserInfoRepo;
import DataBaseProject.phase4.repository.JdbcUserRepo;
import DataBaseProject.phase4.service.JoinService;
import DataBaseProject.phase4.service.ProductAndLikeInfoService;
import DataBaseProject.phase4.service.LoginService;
import DataBaseProject.phase4.service.ReviewService;
import DataBaseProject.phase4.service.ShoppingCartService;
import DataBaseProject.phase4.service.TransactionService;
import DataBaseProject.phase4.service.UpdateInfoService;
import DataBaseProject.phase4.service.UserService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String USER_UNIVERSITY ="SYSTEM";
    public static final String USER_PASSWD ="oracle";
    public static Connection conn;

    public SpringConfig() {
        try{
            conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD);
            System.out.println("Connected.");
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println("Cannot get a connection: " + e.getLocalizedMessage());
            System.err.println("Cannot get a connection: " + e.getMessage());
        }
    }
    @Bean
    public JoinService joinService(){return new JoinService(new JdbcJoinRepo(conn));}

    @Bean
    public LoginService loginService(){return new LoginService(new JdbcLoginRepo(conn));}

    @Bean
    public ProductAndLikeInfoService likeInfoService(){return new ProductAndLikeInfoService(new JdbcLikeInfosRepo(conn), new JdbcProductRepo(conn));}

    @Bean
    public UserService userService(){return new UserService(new JdbcUserRepo(conn));}

    @Bean
    public UpdateInfoService updateInfoService(){return new UpdateInfoService(new JdbcUpdateUserInfoRepo(conn));}

    @Bean
    public ShoppingCartService shoppingCartService(){return new ShoppingCartService(new JdbcShoppingCartRepo(conn));}

    @Bean
    public TransactionService transactionService(){return new TransactionService(new JdbcTransactionInfoRepo(conn));}

    @Bean
    public ReviewService reviewService(){return new ReviewService(new JdbcReviewRepo(conn));}
}
