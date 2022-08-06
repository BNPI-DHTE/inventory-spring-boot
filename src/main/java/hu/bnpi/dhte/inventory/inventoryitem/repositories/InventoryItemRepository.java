package hu.bnpi.dhte.inventory.inventoryitem.repositories;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findByInventoryId(String inventoryId);

    List<InventoryItem> findAllByResponsibleResponsibleNumber(String responsibleNumber);

    @Query("select i from InventoryItem i where upper(i.responsible.name) like concat('%', upper(:responsibleName), '%') ")
    List<InventoryItem> findAllByResponsibleName(@Param("responsibleName") String responsibleName);
}
