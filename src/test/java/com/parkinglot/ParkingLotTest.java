package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_parking_lot_and_car() {
        // Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        //When
        ParkingTicket parkingTicket = parkingLot.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket() {
        // Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket = parkingLot.park(car);
        //When
        Car actualCar = parkingLot.fetch(parkingTicket);
        //Then
        assertEquals(car, actualCar);
    }


    @Test
    void should_return_right_car_when_fetch_given_parking_lot_and_right_ticket() {
        // Given
        Car carBob = new Car();
        Car carAlice = new Car();
        ParkingLot parkingLot = new ParkingLot();

        //When
        ParkingTicket bobParking = parkingLot.park(carBob);
        ParkingTicket aliceParking = parkingLot.park(carAlice);

        Car carOne = parkingLot.fetch(bobParking);
        Car carTwo = parkingLot.fetch(aliceParking);
        //Then
        assertEquals(carBob, carOne);
        assertEquals(carAlice, carTwo);
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_ticket() {
        // Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(car);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //Then
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingLot.fetch(wrongParkingTicket));
        assertEquals("Unrecognized Parking Ticket " + wrongParkingTicket.hashCode()
                , runtimeException.getMessage());

    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_used_ticket() {
        // Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket bobParking = parkingLot.park(car);
        //When
        ParkingTicket usedTicket = new ParkingTicket();
        //Then

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingLot.fetch(usedTicket));
        assertEquals("Unrecognized Parking Ticket " + usedTicket.hashCode()
                , runtimeException.getMessage());
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_full_slot() {
        // Given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        //when
        for (int i = 1; i <=10; i ++) {
            ParkingTicket parkingTicket = parkingLot.park(car);
            //Then
            assertNotNull(parkingTicket);
        }
    }
}
