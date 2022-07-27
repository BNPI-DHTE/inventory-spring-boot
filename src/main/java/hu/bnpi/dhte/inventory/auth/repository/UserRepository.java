package hu.bnpi.dhte.inventory.auth.repository;

import hu.bnpi.dhte.inventory.auth.model.InventoryAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<InventoryAppUser, Long> {

    Optional<InventoryAppUser> findByUsername(String username);
}
