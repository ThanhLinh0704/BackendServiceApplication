package linhlt.project.backend_service.repository;

import linhlt.project.backend_service.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    public boolean  existsByUsername(String username);
    UserEntity findByUsername(String username);
}
