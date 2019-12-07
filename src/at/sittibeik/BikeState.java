package at.sittibeik;

public enum BikeState {
    PENDING, STORAGE, PARKED, RENTED, TO_BE_SERVICED, TO_BE_DISCARDED;

    boolean isRentable() {
        if (this == PARKED) {
            return true;
        }
        return false;
    }
}
