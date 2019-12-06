package at.sittibeik;

class User {
    private static int MAX_USER_ID = 0;

    private int id;
    private String name;
    private String surname;
    private Integer rentedBikeId;

    User(String name, String surname) {
        this.id = ++MAX_USER_ID;
        this.name = name;
        this.surname = surname;
    }

    int getId() { return id; }
    String getName() { return name; }
    String getSurname() { return surname; }

    void setRented(Integer rentedBikeId) {
        this.rentedBikeId = rentedBikeId;
    }

    boolean hasBike() {
        return rentedBikeId != null;
    }
}
