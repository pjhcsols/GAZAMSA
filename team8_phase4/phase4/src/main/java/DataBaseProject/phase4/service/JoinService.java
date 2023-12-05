package DataBaseProject.phase4.service;

import DataBaseProject.phase4.domain.BrandUser;
import DataBaseProject.phase4.domain.NormalUser;
import DataBaseProject.phase4.repository.JdbcJoinRepo;
import DataBaseProject.phase4.repository.Status;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


public class JoinService {

    private final JdbcJoinRepo joinRepo;

    public JoinService(JdbcJoinRepo joinRepo) {
        this.joinRepo = joinRepo;
    }
    public Status join(NormalUser normalUser){
        Status status = joinRepo.checkDuplicate(normalUser.getId(), 1);
        if (status == Status.FAIL)return status;
        status = joinRepo.join(normalUser);
        return status;
    }
    public Status join(BrandUser brandUser){
        Status status = joinRepo.checkDuplicate(brandUser.getId(), 2);
        if (status == Status.FAIL)return status;
        status = joinRepo.join(brandUser);
        return status;
    }

}
