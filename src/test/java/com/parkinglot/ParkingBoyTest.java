package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    void should_return_ticket_when_park_given_parking_lot_and_car_to_parking_bo() {

        // Given
        Car car = new Car();
        parkingLotList.add(new ParkingLot(1));
        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket_to_parking_bo() {
        // Given
        parkingLotList.add(new ParkingLot(1));
        ParkingTicket parkingTicket = parkingBoy.park(car);
        //When
        Car actualCar = parkingBoy.fetch(parkingTicket);
        //Then
        assertEquals(car, actualCar);
    }


    @Test
    void should_return_right_car_when_fetch_given_parking_lot_and_right_ticket_to_parking_bo() {
        // Given
        parkingLotList.add(new ParkingLot(2));
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
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_ticket_to_parking_bo() {
        // Given
        parkingLotList.add(new ParkingLot(1));
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
    void should_return_nothing_when_fetch_given_parking_lot_and_used_ticket_to_parking_boy() {
        parkingLotList.add(new ParkingLot(1));
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
    void should_return_full_when_fetch_given_parking_lot_and_full_slot_to_parking_boy() {
        parkingLotList.add(new ParkingLot(10));
        // Given
        ParkingLot parkingLot = new ParkingLot(10);
        //when
        assertEquals(10, parkingLot.getCapacity());
    }
    @Test
    void should_return_car_ticket_when_parking_given_car_while_parking_lot_1_is_not_max_capacity_to_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(0);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        parkingBoy.setParkingLot(parkingLotList);

        //when
        ParkingTicket parkingTix1 = parkingBoy.park(new Car());
        //then
        assertNotNull(parkingTix1);
    }

    @Test
    void should_return_car_ticket_to_lot_2_when_parking_given_car_while_parking_lot_1_is_at_max_capacity_to_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkinglot2 = new ParkingLot(1);
        Car carBob = new Car();
        Car carAlice = new Car();
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkinglot2);
        parkingBoy.setParkingLot(parkingLotList);
        //when
        ParkingTicket parkingTix1 = parkingBoy.park(carBob);
        ParkingTicket parkingTix2 = parkingBoy.park(carAlice);
        //then
        assertNotNull(parkingTix2);
    }

    @Test
    void should_return_non__when_parking_given_2_lot_is_full_at_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkinglot2 = new ParkingLot(10);
        Car carAlice = new Car();
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkinglot2);
        parkingBoy.setParkingLot(parkingLotList);
        //then

        Exception exception = assertThrows(Exception.class
                , () -> parkingBoy.park(carAlice));
        assertEquals("Not Enough Position", exception.getMessage());
    }


}
