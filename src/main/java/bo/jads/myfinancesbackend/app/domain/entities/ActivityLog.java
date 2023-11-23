package bo.jads.myfinancesbackend.app.domain.entities;

import bo.jads.myfinancesbackend.app.domain.entities.enums.ActivityLogResult;
import bo.jads.myfinancesbackend.app.domain.entities.enums.EntityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "activity_logs")
@Getter
@Setter
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "execution_timestamp", nullable = false)
    private Timestamp executionTimestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false, length = 16)
    private ActivityLogResult result;

    @Column(name = "message", nullable = false, length = 512)
    private String message;

    @Column(name = "affected_entity_id", nullable = false)
    private Long affectedEntityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "affected_entity_type", nullable = false, length = 32)
    private EntityType affectedEntityType;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "action_id", nullable = false)
    private Long actionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Action action;

}
