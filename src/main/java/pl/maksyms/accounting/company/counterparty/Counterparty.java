package pl.maksyms.accounting.company.counterparty;

import pl.maksyms.accounting.company.Company;
import pl.maksyms.accounting.invoice.purchase.PurchaseInvoice;
import pl.maksyms.accounting.invoice.sale.SalesInvoice;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "counterparties")
public class Counterparty extends Company {

    @OneToMany(mappedBy = "seller")
    private List<PurchaseInvoice> purchaseInvoiceList;

    @OneToMany(mappedBy = "buyer")
    private List<SalesInvoice> salesInvoiceList;

    public List<PurchaseInvoice> getPurchaseInvoiceList() {
        return purchaseInvoiceList;
    }

    public void setPurchaseInvoiceList(List<PurchaseInvoice> purchaseInvoiceList) {
        this.purchaseInvoiceList = purchaseInvoiceList;
    }

    public void addPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        this.purchaseInvoiceList.add(purchaseInvoice);
    }

    public List<SalesInvoice> getSalesInvoiceList() {
        return salesInvoiceList;
    }

    public void setSalesInvoiceList(List<SalesInvoice> salesInvoiceList) {
        this.salesInvoiceList = salesInvoiceList;
    }

    public void addSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoiceList.add(salesInvoice);
    }
}
