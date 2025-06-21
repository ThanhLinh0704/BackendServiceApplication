package linhlt.project.backend_service.repository;

import linhlt.project.backend_service.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    AddressEntity findByUserIdAndAddressType(String userId, Integer addressType);
}
