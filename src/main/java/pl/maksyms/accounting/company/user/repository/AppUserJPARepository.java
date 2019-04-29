package pl.maksyms.accounting.company.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.company.user.AppUser;

public interface AppUserJPARepository extends JpaRepository<AppUser, Long> {
}
