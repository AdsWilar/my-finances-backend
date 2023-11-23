package bo.jads.myfinancesbackend.app.domain.entities;

import bo.jads.myfinancesbackend.app.domain.entities.enums.ActionCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "actions")
@Getter
@Setter
public class Action {

    @Id
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", unique = true, nullable = false, length = 64)
    private ActionCode code;

}
