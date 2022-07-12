package hu.bnpi.dhte.inventory.event.model;

import hu.bnpi.dhte.inventory.inventoryitem.model.InventoryItem;
import hu.bnpi.dhte.inventory.responsible.model.Responsible;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "note_number", length = 50)
    private String noteNumber;

    @NotEmpty
    @ManyToMany(mappedBy = "events")
    private List<InventoryItem> items;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "event_type", length = 11, nullable = false)
    private EventType eventType;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "old_responsible")
    private Responsible oldResponsible;

    @OneToOne
    @JoinColumn(name = "new_responsible")
    private Responsible newResponsible;
}
