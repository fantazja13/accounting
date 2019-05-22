package pl.maksyms.accounting.company.user.dto;

import javax.validation.constraints.NotBlank;

public class AppUserAddressDTO {

    @NotBlank
    private String street;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String town;

}
