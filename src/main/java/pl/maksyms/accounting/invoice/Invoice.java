package pl.maksyms.accounting.invoice;

import pl.maksyms.accounting.invoice.item.InvoiceItem;
import pl.maksyms.accounting.security.audit.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Invoice extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String signature;

    @Column(name = "gross")
    private BigDecimal totalGrossAmount;

    @Column(name = "net")
    private BigDecimal totalNetAmount;

    @Column(name = "vat_amount")
    private BigDecimal totalVATAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public BigDecimal getTotalGrossAmount() {
        return totalGrossAmount;
    }

    public void setTotalGrossAmount(BigDecimal totalGrossAmount) {
        this.totalGrossAmount = totalGrossAmount;
    }

    public BigDecimal getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(BigDecimal totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public BigDecimal getTotalVATAmount() {
        return totalVATAmount;
    }

    public void setTotalVATAmount(BigDecimal totalVATAmount) {
        this.totalVATAmount = totalVATAmount;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItems.add(invoiceItem);
    }
}
