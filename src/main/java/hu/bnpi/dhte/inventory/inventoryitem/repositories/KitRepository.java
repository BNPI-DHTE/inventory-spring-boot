package hu.bnpi.dhte.inventory.inventoryitem.repositories;

import hu.bnpi.dhte.inventory.inventoryitem.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRepository extends JpaRepository<Kit, Long> {
}
