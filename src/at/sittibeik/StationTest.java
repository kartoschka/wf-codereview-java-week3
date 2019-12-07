package at.sittibeik;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StationTest {
    private Station station;

    @BeforeEach
    void makeStation() {
        station = new Station("Kackenheimer Weg", 3);
    }

    @Test
    void addBike_throwsWhenFull() {
        assertDoesNotThrow( () -> station.addBike(new Bike("green")) );
        assertDoesNotThrow( () -> station.addBike(new Bike("blue")) );
        assertDoesNotThrow( () -> station.addBike(new Bike("yellow")) );
        assertThrows(StationFullException.class, () -> station.addBike(new Bike("red")));
        assertThrows(StationFullException.class, () -> station.addBike(new Bike("green")));
    }

    @Test
    void addBike_increasesBikeMapSize() {
        assertDoesNotThrow(() -> {
            var bikeMap = station.getBikes();
            assertEquals(0, bikeMap.size());
            station.addBike(new Bike("green"));
            assertEquals(1, bikeMap.size());
            station.addBike(new Bike("green"));
            station.addBike(new Bike("green"));
            assertEquals(3, bikeMap.size());
        });
    }

    @Test
    void addBike_failsAddingSameBikeTwice() {
        var b1 = new Bike("vomit-green");
        var b2 = new Bike("vomit-yellow");
        assertDoesNotThrow(() -> station.addBike(b1));
        assertDoesNotThrow(() -> station.addBike(b2));
        assertThrows(IllegalStateException.class, () -> station.addBike(b1));
        assertThrows(IllegalStateException.class, () -> station.addBike(b2));
    }
}