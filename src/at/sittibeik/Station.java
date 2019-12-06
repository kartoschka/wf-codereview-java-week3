package at.sittibeik;

import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Station {
    private static int MAX_STATION_ID = 0;

    private int id;
    private String location;
    private int capacity;
    private HashMap<Integer, Bike> bikes = new HashMap<>();

    Station(String location, int capacity) {
        this.id = ++MAX_STATION_ID;
        this.location = location;
        this.capacity = capacity;
    }

    int getId() { return id; }
    String getLocation() { return location; }

    String formatRentableList() {
        return formatList(Bike::isRentable);
    }

    private String formatList(Predicate<Bike> filterFun) {
        String header = String.format("Beiks die wos grade do san in da Station %d/\"%s\" die wos du nehman kaunst:\n", id, location);
        String bikeList = bikes.values().stream()
                .filter(filterFun)
                .sorted(Comparator.comparingInt(Bike::getId))
                .map(b -> String.format("  Bike %d, %s", b.getId(), b.getColor()))
                .collect(Collectors.joining("\n"));
        return header + bikeList;
    }

    private int numberOfFreeSlots() {
        return capacity - bikes.size();
    }

    private boolean hasFreeSlots() {
        return numberOfFreeSlots() > 0;
    }

    void addBike(Bike b) throws StationFullException {
        assert bikes.size() <= capacity;
        if(!hasFreeSlots()) throw new StationFullException();

        b.setParked();
        bikes.put(b.getId(), b);
        System.out.println("successfully parked bike " + b.getId());
    }

    void rentBike(User user, Bike bike) {
        if(bike.isRentable() && this.hasBike(bike) && !user.hasBike()) {
            bikes.remove(bike.getId());
            bike.setRented(user.getId());
            user.setRented(bike.getId());
            System.out.printf("Rented bike %d to user %s %s (%d).\n",
                    bike.getId(), user.getName(), user.getSurname(), user.getId());
        } else {
            throw new BikeNotRentableException();
        }
    }

    private boolean hasBike(Bike bike) {
        return bikes.containsKey(bike.getId());
    }
}
