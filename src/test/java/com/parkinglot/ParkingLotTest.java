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
        ParkingTicket newTix = new ParkingTicket();

        //When
        ParkingTicket bobParking = parkingLot.park(car);

        Car bobCar = parkingLot.fetch(newTix);

        //Then
        assertNull(bobCar);

    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_used_ticket() {
        // Given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket bobParking = parkingLot.park(car);

        //When
        Car bobCar = parkingLot.fetch(bobParking);
        Car aliceCar = parkingLot.fetch(bobParking);

        //Then
        assertNull(aliceCar);

    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_full_slot() {
        // Given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        //when
        for (int i = 0; i <10; i ++) {
            ParkingTicket parkingTicket = parkingLot.park(car);
        }
        Car bobCar = new Car();

        ParkingTicket newTickets = parkingLot.park(bobCar);
        //Then
        assertNull(newTickets);

    }
}
