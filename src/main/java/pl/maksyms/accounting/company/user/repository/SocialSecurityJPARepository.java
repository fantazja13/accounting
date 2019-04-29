package pl.maksyms.accounting.company.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.company.user.SocialSecurity;

public interface SocialSecurityJPARepository extends JpaRepository<SocialSecurity, Long> {
}
