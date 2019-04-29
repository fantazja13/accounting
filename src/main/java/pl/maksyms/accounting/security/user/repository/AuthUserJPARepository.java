package pl.maksyms.accounting.security.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.security.user.AuthUser;

import java.util.Optional;

public interface AuthUserJPARepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);

}
