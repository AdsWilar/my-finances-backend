package bo.jads.myfinancesbackend.app.domain.repositories;

import bo.jads.myfinancesbackend.app.domain.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
