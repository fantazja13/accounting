package pl.maksyms.accounting.company.user;

import pl.maksyms.accounting.security.audit.Auditable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class SocialSecurity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private BigDecimal basis;

    private boolean startRelief;

    private LocalDate startReliefEndDate;

    private boolean preferential;

    private LocalDate preferentialEndDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBasis() {
        return basis;
    }

    public void setBasis(BigDecimal basis) {
        this.basis = basis;
    }

    public boolean isStartRelief() {
        return startRelief;
    }

    public void setStartRelief(boolean startRelief) {
        this.startRelief = startRelief;
    }

    public LocalDate getStartReliefEndDate() {
        return startReliefEndDate;
    }

    public void setStartReliefEndDate(LocalDate startReliefEndDate) {
        this.startReliefEndDate = startReliefEndDate;
    }

    public boolean isPreferential() {
        return preferential;
    }

    public void setPreferential(boolean preferential) {
        this.preferential = preferential;
    }

    public LocalDate getPreferentialEndDate() {
        return preferentialEndDate;
    }

    public void setPreferentialEndDate(LocalDate preferentialEndDate) {
        this.preferentialEndDate = preferentialEndDate;
    }
}
