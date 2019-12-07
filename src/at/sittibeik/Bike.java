package at.sittibeik;

class Bike {
    private static int MAX_BIKE_ID = 0;

    private int id;
    private String color;
    private BikeState state;

    private Integer rentedByUserId;

    Bike(String color) {
        this.id = ++MAX_BIKE_ID;
        this.color = color;
        this.state = BikeState.STORAGE;
    }

    int getId() { return id; }
    String getColor() { return color; }

    @Override
    public String toString() {
        return String.format("Bike { id: %d, color: %s, state: %s, rented-by: %d }", id, color, state, rentedByUserId);
    }

    void setRented(int userId) {
        state = BikeState.RENTED;
        rentedByUserId = userId;
    }

    void setParked() {
        state = BikeState.PARKED;
        rentedByUserId = null;
    }

    void setPending() {
        state = BikeState.PENDING;
    }

    boolean isRentable() {
        return state == BikeState.PARKED;
    }

    boolean isRented() {
        return state == BikeState.RENTED;
    }
}
