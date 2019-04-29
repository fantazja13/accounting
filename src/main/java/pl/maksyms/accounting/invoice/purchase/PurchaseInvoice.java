package pl.maksyms.accounting.invoice.purchase;

import pl.maksyms.accounting.company.user.AppUser;
import pl.maksyms.accounting.company.counterparty.Counterparty;
import pl.maksyms.accounting.invoice.Invoice;

import javax.persistence.*;

@Entity
@Table(name = "purchase_invoices")
public class PurchaseInvoice extends Invoice {

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Counterparty seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private AppUser buyer;

    public Counterparty getSeller() {
        return seller;
    }

    public void setSeller(Counterparty seller) {
        this.seller = seller;
    }

    public AppUser getBuyer() {
        return buyer;
    }

    public void setBuyer(AppUser buyer) {
        this.buyer = buyer;
    }
}
