package pl.maksyms.accounting.company.user;

import pl.maksyms.accounting.security.audit.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "consents")
public class ContactConsent extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "general")
    private boolean generalEmailConsent;

    @Column(name = "social_security")
    private boolean socialSecurityConsent;

    @Column(name = "income_tax")
    private boolean incomeTaxConsent;

    @Column(name = "vat")
    private boolean VATConsent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isGeneralEmailConsent() {
        return generalEmailConsent;
    }

    public void setGeneralEmailConsent(boolean generalEmailConsent) {
        this.generalEmailConsent = generalEmailConsent;
    }

    public boolean isSocialSecurityConsent() {
        return socialSecurityConsent;
    }

    public void setSocialSecurityConsent(boolean socialSecurityConsent) {
        this.socialSecurityConsent = socialSecurityConsent;
    }

    public boolean isIncomeTaxConsent() {
        return incomeTaxConsent;
    }

    public void setIncomeTaxConsent(boolean incomeTaxConsent) {
        this.incomeTaxConsent = incomeTaxConsent;
    }

    public boolean isVATConsent() {
        return VATConsent;
    }

    public void setVATConsent(boolean VATConsent) {
        this.VATConsent = VATConsent;
    }
}
