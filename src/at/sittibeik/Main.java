package at.sittibeik;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Bike[] exampleBikes = createBikes();
        User[] exampleUsers = createUsers();
        Station[] exampleStations = createStations();

        HashMap<Integer, Bike> bikeMap = createBikeMap(exampleBikes);
        HashMap<Integer, Station> stationMap = createStationMap(exampleStations);

        Station bikeRentalExampleStation = exampleStations[2];
        Station bikeRentalExampleReturnStation = exampleStations[1];
        fillStationWithBikes(bikeRentalExampleStation,
                new Bike[]{exampleBikes[0], exampleBikes[1], exampleBikes[6], exampleBikes[7]});
        fillStationWithBikes(bikeRentalExampleReturnStation,
                new Bike[]{exampleBikes[2], exampleBikes[3], exampleBikes[5]});
        runBikeRentalDemo(exampleUsers[1], bikeRentalExampleStation, exampleBikes[7], bikeRentalExampleReturnStation);
    }

    private static void runBikeRentalDemo(User user, Station station, Bike bike, Station station2) {
        System.out.print("It is a cold rainy night in the dirty streets of Vienna, and ");
        System.out.printf("%s %s ", user.getName(), user.getSurname());
        System.out.printf("is standing at the sitybeik station %d/%s ", station.getId(), station.getLocation());
        System.out.print("because the damn subway network has broken down again.\n");
        System.out.println();
        System.out.println("The list of available bikes is showing on the flickering screen: ");
        System.out.println();
        System.out.println("\"\"\"");
        System.out.println(station.formatRentableList());
        System.out.println("\"\"\"");
        System.out.println();
        System.out.printf("%s decides to take bike %d because %s is the coolest color.\n",
                user.getName(), bike.getId(), bike.getColor());
        System.out.println();
        System.out.println("\"\"\"");
        String preRentStr = bike.toString();
        Rental rental = new Rental(user, bike, station);
        String postRentStr = bike.toString();
        System.out.println("\"\"\"");
        System.out.println();
        System.out.println("Bike info before and after renting:");
        System.out.println(preRentStr);
        System.out.println(postRentStr);
        System.out.println();
        System.out.println("Now the list of available bikes reads:");
        System.out.println();
        System.out.println("\"\"\"");
        System.out.println(station.formatRentableList());
        System.out.println("\"\"\"");
        System.out.println();
        System.out.printf("%s swiftly sweeps into the bike seat. Bon voyage, %s!\n", user.getName(), user.getName());
        System.out.println();
        System.out.print("After a long, perilous journey at the mercy of the 75cm-wide Fahrradstreifen, ");
        System.out.printf("%s finally arrives at destination station %d/\"%s\" and returns the bike.\n",
                user.getName(), station2.getId(), station2.getLocation());
        System.out.println();
        System.out.println("\"\"\"");
        System.out.println(station2.formatRentableList());
        System.out.println("\"\"\"");
        System.out.println();
        String preChoutStr = bike.toString();
        rental.checkOut(station2);
        String postChoutStr = bike.toString();
        System.out.println();
        System.out.println("\"\"\"");
        System.out.println(station2.formatRentableList());
        System.out.println("\"\"\"");
        System.out.println();
        System.out.println("Bike info before and after checkout:");
        System.out.println(preChoutStr);
        System.out.println(postChoutStr);
    }

    private static Bike[] createBikes() {
        return new Bike[]{
                new Bike("red"),
                new Bike("black"),
                new Bike("black"),
                new Bike("blue"),
                new Bike("pink"),
                new Bike("orange"),
                new Bike("green"),
                new Bike("pink"),
        };
    }

    private static HashMap<Integer, Bike> createBikeMap(Bike[] bikes) {
        HashMap<Integer, Bike> map = new HashMap<>();
        for (Bike b : bikes) map.put(b.getId(), b);
        return map;
    }

    private static User[] createUsers() {
        return new User[] {
            new User("Günther", "Faulenbacher"),
            new User("Maximilian", "Schweinsgericht"),
            new User("Linda", "Bauernschreck"),
            new User("Ork", "Van Groknok")
        };
    }

    private static Station[] createStations() {
        return new Station[]{
                new Station("Grunzinger Straße 7", 22),
                new Station("Kotzendorfer Platz 18-20", 15),
                new Station("Am Stinkbrunnen 1", 26)
        };
    }

    private static void fillStationWithBikes(Station s, Bike[] bs) {
        for (Bike b : bs) {
            try {
                s.addBike(b);
            } catch (StationFullException e) {
                System.out.println("Cannot add any more bikes to station because it is full!");
            }
        }
    }

    private static HashMap<Integer, Station> createStationMap(Station[] stations) {
        HashMap<Integer, Station> map = new HashMap<>();
        for (Station b : stations) map.put(b.getId(), b);
        return map;
    }
}
