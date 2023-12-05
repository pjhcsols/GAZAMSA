package DataBaseProject.phase4.service;

import DataBaseProject.phase4.repository.JdbcLoginRepo;
import DataBaseProject.phase4.repository.JdbcUpdateUserInfoRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


public class UpdateInfoService {

    private final JdbcUpdateUserInfoRepo updateUserInfoRepo;


    public UpdateInfoService(JdbcUpdateUserInfoRepo updateUserInfoRepo) {
        this.updateUserInfoRepo = updateUserInfoRepo;
    }

    public Status updateInfo(String attributeName, String attribute, String normalUserId){
        return updateUserInfoRepo.updateUserInfo(attributeName, attribute, normalUserId);
    }
    public Status updateInfo(String attributeName, Long attribute, String normalUserId){
        return updateUserInfoRepo.updateUserInfo(attributeName, attribute, normalUserId);
    }
}
