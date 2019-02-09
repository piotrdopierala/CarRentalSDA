package pl.dopierala.carRent.domain;

public enum CarStatusEnum {
    RENTED("RENTED"), AVAILABLE("AVAILABLE"), UNAVAILABLE("UnAvailable");

    private final String statusName;

    CarStatusEnum(String statusName) {
        this.statusName=statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
