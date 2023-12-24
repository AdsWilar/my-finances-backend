package bo.jads.myfinancesbackend.app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account_actions")
@Getter
@Setter
public class AccountAction {

    @Id
    private Long id;

    @Column(name = "action_id", nullable = false)
    private Long actionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Action action;

}
