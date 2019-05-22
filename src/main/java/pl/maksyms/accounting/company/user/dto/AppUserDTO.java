package pl.maksyms.accounting.company.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AppUserDTO {

    @NotBlank
    private String companyName;
    @NotBlank
    private String taxNumber;
    @NotBlank
    private String incomeTax;
    @JsonProperty(value = "VATPayor")
    private boolean VATPayor;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(String incomeTax) {
        this.incomeTax = incomeTax;
    }

    public boolean isVATPayor() {
        return VATPayor;
    }

    public void setVATPayor(boolean VATPayor) {
        this.VATPayor = VATPayor;
    }
}
