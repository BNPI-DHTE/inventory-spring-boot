package hu.bnpi.dhte.inventory.inventoryitem.repositories;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findByInventoryId(String inventoryId);
}
