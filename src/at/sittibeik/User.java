package at.sittibeik;

class User {
    private static int MAX_USER_ID = 0;

    private int id;
    private String name;
    private String surname;
    private Integer rentedBikeId;

    public User(String name, String surname) {
        this.id = ++MAX_USER_ID;
        this.name = name;
        this.surname = surname;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }

    public void setRented(Integer rentedBikeId) {
        this.rentedBikeId = rentedBikeId;
    }

    public boolean hasBike() {
        return rentedBikeId != null;
    }
}
