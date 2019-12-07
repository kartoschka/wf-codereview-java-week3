package at.sittibeik;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.TestInstantiationException;
import org.junit.platform.commons.JUnitException;

class RentalTest {
    private User user;
    private Bike bike;
    private Station station;
    private Rental rental;

    @BeforeEach
    void prepareElements() {
        user = new User("Hieronymus", "Müllheimer");
        bike = new Bike("porno-gold");
        station = new Station("Am letzthintersten Loch 8", 5);
    }

    @BeforeEach
    void prepareRental() {
        var u = new User("Karl", "Krätzenquell");
        var b = new Bike("shock-pink");
        var s = new Station("Platz-der-lebenden-Toten 7", 7);
        try {
            s.addBike(b);
            rental = new Rental(u, b, s);
        } catch (Exception e) {
            throw new JUnitException("WHOOPS");
        }
    }

    @Test
    void createRental_throwsIfBikeNotAtStation_andInverse() {
        assertThrows(Exception.class, () -> new Rental(user, bike, station));
        assertDoesNotThrow(() -> station.addBike(bike));
        assertDoesNotThrow(() -> new Rental(user, bike, station));
    }

    @Test
    void checkoutFailsAtFullStation_andInverse() {
        var s = new Station("Minimundus 1", 1);
        assertDoesNotThrow(() -> s.addBike(new Bike("blue")));
        assertThrows(Exception.class, () -> rental.checkOut(s));
        assertNull(rental.getRentEnd());
        assertDoesNotThrow(() -> rental.checkOut(station));
        assertNotNull(rental.getRentEnd());
    }

    @Test
    void rentCheckinCheckoutProcessesSetCorrectBooleanFlags() {
        assertFalse(station.hasBike(bike));
        assertFalse(user.hasBike());
        assertFalse(bike.isRented());
        assertFalse(bike.isRentable()); // bike should not be rentable if not registered with station

        assertDoesNotThrow(() -> station.addBike(bike));
        assertTrue(station.hasBike(bike));
        assertFalse(user.hasBike());
        assertFalse(bike.isRented());
        assertTrue(bike.isRentable());

        var r = new Rental(user, bike, station);
        assertFalse(station.hasBike(bike));
        assertTrue(user.hasBike());
        assertTrue(bike.isRented());
        assertFalse(bike.isRentable());

        r.checkOut(station);
        assertTrue(station.hasBike(bike));
        assertFalse(user.hasBike());
        assertFalse(bike.isRented());
        assertTrue(bike.isRentable());
    }
}