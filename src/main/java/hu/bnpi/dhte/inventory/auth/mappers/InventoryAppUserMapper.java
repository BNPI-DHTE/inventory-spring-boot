package hu.bnpi.dhte.inventory.auth.mappers;

import hu.bnpi.dhte.inventory.auth.dtos.InventoryAppUserDetails;
import hu.bnpi.dhte.inventory.auth.model.InventoryAppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryAppUserMapper {

    InventoryAppUserDetails toInventoryAppUserDetails(InventoryAppUser user);
}
