package pl.maksyms.accounting.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.invoice.Invoice;

public interface InvoiceJPARepository extends JpaRepository<Invoice, Long> {
}
