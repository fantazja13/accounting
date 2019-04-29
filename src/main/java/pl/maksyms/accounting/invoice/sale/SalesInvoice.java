package pl.maksyms.accounting.invoice.sale;

import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.company.counterparty.Counterparty;
import pl.maksyms.accounting.invoice.Invoice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sales_invoices")
public class SalesInvoice extends Invoice {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private AppUser seller;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Counterparty buyer;

    public AppUser getSeller() {
        return seller;
    }

    public void setSeller(AppUser seller) {
        this.seller = seller;
    }

    public Counterparty getBuyer() {
        return buyer;
    }

    public void setBuyer(Counterparty buyer) {
        this.buyer = buyer;
    }
}
