package pl.maksyms.accounting.company.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.maksyms.accounting.company.Company;
import pl.maksyms.accounting.invoice.purchase.PurchaseInvoice;
import pl.maksyms.accounting.invoice.sale.SalesInvoice;
import pl.maksyms.accounting.security.user.AuthUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_users")
@JsonIgnoreProperties({"createdTime", "createdBy", "updatedTime", "updatedBy"})
public class AppUser extends Company {

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private AuthUser authUser;

    private boolean VATPayor;

    private IncomeTaxType incomeTax;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "buyer")
    private List<PurchaseInvoice> purchaseInvoiceList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "seller")
    private List<SalesInvoice> salesInvoiceList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "social_sec_id")
    private SocialSecurity socialSecurity;

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public boolean isVATPayor() {
        return VATPayor;
    }

    public void setVATPayor(boolean VATPayor) {
        this.VATPayor = VATPayor;
    }

    public IncomeTaxType getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(IncomeTaxType incomeTax) {
        this.incomeTax = incomeTax;
    }

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

    public SocialSecurity getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(SocialSecurity socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

}
