package pl.maksyms.accounting.invoice.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.invoice.item.InvoiceItem;

public interface InvoiceItemJPARepostiory extends JpaRepository<InvoiceItem, Long> {
}
