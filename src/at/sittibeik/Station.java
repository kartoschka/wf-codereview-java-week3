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
    HashMap<Integer, Bike> getBikes() { return bikes; }

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
        if(hasBike(b)) throw new IllegalStateException("bike already exists at station: " + b.getId());

        b.setParked();
        bikes.put(b.getId(), b);
        System.out.println("successfully parked bike " + b.getId());
    }

    void removeBike(Bike bike) {
        if(!hasBike(bike)) throw new IllegalStateException("cannot remove Bike which does not exist at Station");
        bikes.remove(bike.getId());
        bike.setPending();
    }

    boolean hasBike(Bike bike) {
        return bikes.containsKey(bike.getId());
    }
}
