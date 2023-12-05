package DataBaseProject.phase4.service;

import java.util.ArrayList;
import DataBaseProject.phase4.domain.ShoppingCart;
import DataBaseProject.phase4.repository.JdbcShoppingCartRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


public class ShoppingCartService {

    private final JdbcShoppingCartRepo jdbcShoppingCartRepo;

    public ShoppingCartService(JdbcShoppingCartRepo jdbcShoppingCartRepo) {
        this.jdbcShoppingCartRepo = jdbcShoppingCartRepo;
    }

    public Status addProduct(String userId, Long productId, Long cnt){
        return jdbcShoppingCartRepo.addProductInShoppingCart(userId, productId, cnt);
    }

    public ArrayList<ShoppingCart> buyProductsInShoppingCart(String userid){
        return jdbcShoppingCartRepo.buyProductsInShoppingCart(userid);
    }

    public ArrayList<ShoppingCart> checkProductsInShoppingCart(String userId){
        ArrayList<ShoppingCart> cart = new ArrayList<>();

        ArrayList<ShoppingCart> carts = jdbcShoppingCartRepo.getShoppingCartInfos();

        for(int i = 0; i < carts.size(); i++){
            if (carts.get(i).getUserId().equals(userId)){
                cart.add(carts.get(i));
            }
        }

        return cart;
    }

    public Status deleteProduct(String userId){
        return jdbcShoppingCartRepo.deleteShoppingCart(userId);
    }
}
