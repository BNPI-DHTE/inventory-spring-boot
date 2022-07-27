package hu.bnpi.dhte.inventory.auth.mappers;

import hu.bnpi.dhte.inventory.auth.dtos.InventoryAppRoleDetails;
import hu.bnpi.dhte.inventory.auth.model.InventoryAppRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryAppRoleMapper {

    InventoryAppRoleDetails toInventoryAppRoleDetails(InventoryAppRole role);
}
