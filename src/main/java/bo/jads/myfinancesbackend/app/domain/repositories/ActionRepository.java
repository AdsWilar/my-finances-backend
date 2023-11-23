package bo.jads.myfinancesbackend.app.domain.repositories;

import bo.jads.myfinancesbackend.app.domain.entities.Action;
import bo.jads.myfinancesbackend.app.domain.entities.enums.ActionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    Optional<Action> findByCode(ActionCode code);

}
