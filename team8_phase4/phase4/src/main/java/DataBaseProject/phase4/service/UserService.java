package DataBaseProject.phase4.service;

import DataBaseProject.phase4.domain.NormalUser;
import DataBaseProject.phase4.repository.JdbcUserRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class UserService {

    private final JdbcUserRepo jdbcUserRepo;


    public UserService(JdbcUserRepo jdbcUserRepo) {
        this.jdbcUserRepo = jdbcUserRepo;
    }

    public Status deleteNormalUser(String userId){
        return jdbcUserRepo.deleteNormalUser(userId);
    }

    public Status deleteBrandUser(String userId){
        return jdbcUserRepo.deleteBrandUser(userId);
    }

    public NormalUser getUserInfo(String userId) {return jdbcUserRepo.getUserInfo(userId);}
}
