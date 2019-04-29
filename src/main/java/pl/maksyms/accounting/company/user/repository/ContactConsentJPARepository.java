package pl.maksyms.accounting.company.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.company.user.ContactConsent;

public interface ContactConsentJPARepository extends JpaRepository<ContactConsent, Long> {
}
