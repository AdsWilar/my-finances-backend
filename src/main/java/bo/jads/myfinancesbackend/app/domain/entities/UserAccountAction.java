package bo.jads.myfinancesbackend.app.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_account_actions")
@Getter
@Setter
public class UserAccountAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "user_account_id", nullable = false)
    private Long userAccountId;

    @Column(name = "account_action_id", nullable = false)
    private Long accountActionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_action_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AccountAction accountAction;

}
