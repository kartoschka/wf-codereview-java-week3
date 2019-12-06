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
    public String getColor() { return color; }

    void setRented(int userId) {
        state = BikeState.RENTED;
        rentedByUserId = userId;
    }

    void setParked() {
        state = BikeState.PARKED;
        rentedByUserId = null;
    }

    boolean isRentable() {
        return state == BikeState.PARKED;
    }
}
