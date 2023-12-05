package DataBaseProject.phase4.service;

import DataBaseProject.phase4.repository.JdbcLoginRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


public class LoginService {
    private final JdbcLoginRepo loginRepo;

    public LoginService(JdbcLoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }
    public Status normalLogin(String normalUserId, String passWord){
        return loginRepo.normalUserLogin(normalUserId, passWord);
    }
    public Status brandLogin(String brandUserId, String passWord){
        return loginRepo.brandUserLogin(brandUserId, passWord);
    }
    public Status adminLogin(String adminId, String passWord){
        return loginRepo.adminLogin(adminId, passWord);
    }
}