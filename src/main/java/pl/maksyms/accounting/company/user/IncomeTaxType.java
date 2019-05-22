package pl.maksyms.accounting.company.user;

public enum IncomeTaxType {
    FLAT_RATE,
    PROGRESSIVE,
    LUMP_SUM;

    public static IncomeTaxType fromString(String taxType) {
        String formattedTaxType = taxType.toLowerCase().trim();
        switch (formattedTaxType) {
            case "flat rate": return FLAT_RATE;
            case "progressive": return PROGRESSIVE;
            case "lump sum": return LUMP_SUM;
            default: return PROGRESSIVE;
        }
    }
}
