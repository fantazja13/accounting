package pl.maksyms.accounting.company.counterparty;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.company.counterparty.Counterparty;

public interface CounterpartyJPARepository extends JpaRepository<Counterparty, Long> {
}
