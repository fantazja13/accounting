package pl.maksyms.accounting.company.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum IncomeTaxType {
    @JsonProperty("flat rate")
    FLAT_RATE,
    @JsonProperty("progressive")
    PROGRESSIVE,
    @JsonProperty("lump sum")
    LUMP_SUM;

    public static IncomeTaxType fromString(String taxType) {
        String formattedTaxType = taxType.toLowerCase().trim();
        switch (formattedTaxType) {
            case "flat rate": return FLAT_RATE;
            case "progressive": return PROGRESSIVE;
            case "lump sum": return LUMP_SUM;
            default: return null;
        }
    }
}
