package hu.bnpi.dhte.security.auth;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permissions")
public class InventoryUserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<InventoryUserRoles> roles;

    public InventoryUserPermission() {
    }

    public InventoryUserPermission(String name, String description, Set<InventoryUserRoles> roles) {
        this.name = name;
        this.description = description;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<InventoryUserRoles> getRoles() {
        return roles;
    }
}
