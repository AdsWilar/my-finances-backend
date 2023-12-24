package bo.jads.myfinancesbackend.app.domain.repositories;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccountAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountActionRepository extends JpaRepository<UserAccountAction, Long> {
}
