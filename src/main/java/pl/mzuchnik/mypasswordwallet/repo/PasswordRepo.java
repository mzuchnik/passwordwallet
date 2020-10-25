package pl.mzuchnik.mypasswordwallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mzuchnik.mypasswordwallet.entity.Password;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordRepo extends JpaRepository<Password, Long> {

    Optional<Password> findById(Long id);

    List<Password> findByUserId(Long id);

}
