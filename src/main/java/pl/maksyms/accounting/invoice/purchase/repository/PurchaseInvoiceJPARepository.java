package pl.maksyms.accounting.invoice.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.invoice.purchase.PurchaseInvoice;

public interface PurchaseInvoiceJPARepository extends JpaRepository<PurchaseInvoice, Long> {
}
