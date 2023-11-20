package bo.jads.myfinancesbackend.app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "initial_balance", nullable = false, precision = 11, scale = 6)
    private BigDecimal initialBalance;

    @Column(name = "total_income", nullable = false, precision = 11, scale = 6)
    private BigDecimal totalIncome;

    @Column(name = "total_expense", nullable = false, precision = 11, scale = 6)
    private BigDecimal totalExpense;

    @Column(name = "current_balance", nullable = false, precision = 11, scale = 6)
    private BigDecimal currentBalance;

    @Column(name = "icon", length = 64)
    private String icon;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Currency currency;

    @PrePersist
    @PreUpdate
    private void prePersistOrUpdate() {
        name = name.trim().toUpperCase();
    }

}
