package pl.maksyms.accounting.invoice.item;

import pl.maksyms.accounting.invoice.Invoice;
import pl.maksyms.accounting.security.audit.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "net")
    private BigDecimal netAmount;

    @Column(name = "gross")
    private BigDecimal grossAmount;

    @Column(name = "vat_amount")
    private BigDecimal VATAmount;

    @Column(name = "vat_rate")
    private Integer VATRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public BigDecimal getVATAmount() {
        return VATAmount;
    }

    public void setVATAmount(BigDecimal VATAmount) {
        this.VATAmount = VATAmount;
    }

    public Integer getVATRate() {
        return VATRate;
    }

    public void setVATRate(Integer VATRate) {
        this.VATRate = VATRate;
    }
}
