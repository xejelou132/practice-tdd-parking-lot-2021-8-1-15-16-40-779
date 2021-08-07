package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    private Car car;
    private List<ParkingLot> parkingLotList;
    private ParkingBoy parkingBoy;


    @BeforeEach
    void setUp() {
        car = new Car();
        parkingLotList = new ArrayList<>();
        parkingBoy = new ParkingBoy(parkingLotList);
    }

    @Test
    void should_return_ticket_when_park_given_parking_lot_and_car() {

        // Given
        Car car = new Car();
        parkingLotList.add(new ParkingLot());
        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket() {
        // Given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //When
        Car actualCar = parkingBoy.fetch(parkingTicket);
        //Then
        assertEquals(car, actualCar);
    }


    @Test
    void should_return_right_car_when_fetch_given_parking_lot_and_right_ticket() {
        // Given
        parkingLotList.add(new ParkingLot());
        Car carBob = new Car();
        Car carAlice = new Car();

        //When
        ParkingTicket bobParking = parkingBoy.park(carBob);
        ParkingTicket aliceParking = parkingBoy.park(carAlice);

        Car carOne = parkingBoy.fetch(bobParking);
        Car carTwo = parkingBoy.fetch(aliceParking);
        //Then
        assertEquals(carBob, carOne);
        assertEquals(carAlice, carTwo);
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_ticket() {
        // Given
        parkingLotList.add(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //Then
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetch(wrongParkingTicket));
        assertEquals("Unrecognized Parking Ticket " + wrongParkingTicket.hashCode()
                , runtimeException.getMessage());

    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_used_ticket() {
        parkingLotList.add(new ParkingLot());
        // Given
        ParkingTicket bobParking = parkingBoy.park(car);
        //When
        ParkingTicket usedTicket = new ParkingTicket();
        //Then

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetch(usedTicket));
        assertEquals("Unrecognized Parking Ticket " + usedTicket.hashCode()
                , runtimeException.getMessage());
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_full_slot() {
        parkingLotList.add(new ParkingLot());
        // Given
        ParkingLot parkingLot = new ParkingLot();
        //when
        assertEquals(10, parkingLot.getCapacity());
    }
}
