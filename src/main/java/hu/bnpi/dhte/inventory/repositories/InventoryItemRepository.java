package hu.bnpi.dhte.inventory.repositories;

import hu.bnpi.dhte.inventory.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
