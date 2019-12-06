package at.sittibeik;

public enum BikeState {
    STORAGE, PARKED, RENTED, TO_BE_SERVICED, TO_BE_DISCARDED;

    boolean isRentable() {
        switch (this) {
            case PARKED:
                return true;
            default:
                return false;
        }
    }
}
