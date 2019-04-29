package pl.maksyms.accounting.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.company.Company;

public interface CompanyJPARepostiory extends JpaRepository<Company, Long> {
}
