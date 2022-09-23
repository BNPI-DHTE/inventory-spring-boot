package hu.bnpi.dhte.security.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class InventoryUserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<InventoryUserPermission> inventoryUserPermissions;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
