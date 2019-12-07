package at.sittibeik;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class RentalTest {
    private User user;
    private Bike bike;
    private Station station;

    @BeforeEach
    void prepare() {
        user = new User("Hieronymus", "Müllheimer");
        bike = new Bike("porno-gold");
        station = new Station("Am letzthintersten Loch 8", 5);
    }

    @Test
    void createRental_throwsIfBikeNotAtStation_andInverse() {
        assertThrows(Exception.class, () -> new Rental(user, bike, station));
        assertDoesNotThrow(() -> station.addBike(bike));
        assertDoesNotThrow(() -> new Rental(user, bike, station));
    }
}