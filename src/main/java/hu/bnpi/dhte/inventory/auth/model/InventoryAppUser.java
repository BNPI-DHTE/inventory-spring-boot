package hu.bnpi.dhte.inventory.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class InventoryAppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false, length = 254)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<InventoryAppRole> roles = new ArrayList<>();

    public void addRole(InventoryAppRole role) {
        this.roles.add(role);
    }
}
