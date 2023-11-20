package bo.jads.myfinancesbackend.app.domain.repositories;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUserIdAndAccountName(Long userId, String accountName);

    Optional<UserAccount> findByIsActiveTrueAndIsOwnerTrueAndUserIdAndAccountId(Long userId, Long accountId);

    Optional<UserAccount> findByUserIdAndAccountId(Long userId, Long accountId);

    List<UserAccount> getAllByIsActiveTrueAndAccountId(Long accountId);

}
