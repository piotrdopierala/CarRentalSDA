package pl.dopierala.carRent.domain;

public enum CarTypesEnum {
    SEDAN("SEDAN"), HATCHBACK("HATCHBACK"), PICKUP("PICKUP"), COMBI("COMBI");

    private final String value;

    CarTypesEnum(String value) {
        this.value=value;
    }

    public String getValue() {
        return this.value;
    }
}
