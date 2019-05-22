package pl.maksyms.accounting.company.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.company.user.AppUser;

import java.util.Optional;

public interface AppUserJPARepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByAuthUserUsername(String username);
}
