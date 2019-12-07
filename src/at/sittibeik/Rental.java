package at.sittibeik;

import java.time.LocalDateTime;

class Rental {
    private User user;
    private Bike bike;
    private Station originStation;
    private LocalDateTime rentStart;
    private LocalDateTime rentEnd;

    Rental(User user, Bike bike, Station originStation) {
        if(!originStation.hasBike(bike)) throw new IllegalStateException("bike not at station");
        if(user.hasBike()) throw new IllegalStateException("user has already rented a bike");
        if(bike.isRented()) throw new IllegalStateException("bike has already been rented");

        this.user = user;
        this.bike = bike;
        this.originStation = originStation;

        checkIn();
    }

    private void checkIn() {
        originStation.removeBike(bike);
        user.setRented(bike.getId());
        bike.setRented(user.getId());

        rentStart = LocalDateTime.now();
        System.out.printf("[%s]: Successfully started bike rental of bike %d to user %d\n",
                rentStart, bike.getId(), user.getId());
    }

    void checkOut(Station station) {
        try { station.addBike(bike); } catch(StationFullException e) {
            System.out.println("cannot return bike at full station " + station.getId());
            throw new IllegalStateException("invoked rental return at full station");
        }

        user.setRented(null);
        bike.setParked();

        rentEnd = LocalDateTime.now();
        System.out.printf("[%s]: Successfully ended bike rental of bike %d to user %d at station %d\n",
                rentEnd, bike.getId(), user.getId(), station.getId());
    }
}
