package pl.maksyms.accounting.company.user.dto;

import javax.validation.constraints.NotBlank;

public class AppUserAddressDTO {

    @NotBlank
    private String street;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String town;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
