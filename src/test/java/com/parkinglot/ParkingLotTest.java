package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_parking_lot_and_car(){
        // Given
        Car car = new Car();
        ParkingLot  parkingLot = new ParkingLot();
        //When
        ParkingTicket parkingTicket = parkingLot.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

//    Given a parking lot, a ticket
//    When fetch the car
//    Then return a car
    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket(){
        // Given
        Car car = new Car();
        ParkingLot  parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();
        //When
        Car actualCar = parkingLot.fetch(parkingTicket);
        //Then
        assertEquals(car , actualCar);

    }

}
