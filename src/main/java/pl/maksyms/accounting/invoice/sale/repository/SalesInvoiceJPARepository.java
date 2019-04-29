package pl.maksyms.accounting.invoice.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.invoice.sale.SalesInvoice;

public interface SalesInvoiceJPARepository extends JpaRepository<SalesInvoice, Long> {
}
