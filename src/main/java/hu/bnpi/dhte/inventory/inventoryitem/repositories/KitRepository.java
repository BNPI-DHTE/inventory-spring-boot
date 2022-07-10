package hu.bnpi.dhte.inventory.inventoryitem.repositories;

import hu.bnpi.dhte.inventory.inventoryitem.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitRepository extends JpaRepository<Kit, Long> {
}
