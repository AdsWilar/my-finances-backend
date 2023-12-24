package bo.jads.myfinancesbackend.app.domain.repositories;

import bo.jads.myfinancesbackend.app.domain.entities.AccountAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountActionRepository extends JpaRepository<AccountAction, Long> {
}
