package bo.jads.myfinancesbackend.app.domain.entities;

import bo.jads.myfinancesbackend.app.domain.entities.enums.CurrencyAcronym;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "currencies")
@Getter
@Setter
public class Currency {

    @Id
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "acronym", unique = true, nullable = false, length = 16)
    private CurrencyAcronym acronym;

    @Column(name = "symbol", nullable = false, length = 16)
    private String symbol;

}
