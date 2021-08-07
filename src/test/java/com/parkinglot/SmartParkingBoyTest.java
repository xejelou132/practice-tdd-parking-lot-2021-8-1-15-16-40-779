package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    private Car car;
    private List<ParkingLot> parkingLotList;
    private SmartParkingBoy smartParkingBoy;

    @BeforeEach
    void setUp() {
        car = new Car();
        parkingLotList = new ArrayList<>();
        smartParkingBoy = new SmartParkingBoy (parkingLotList);
    }
    @Test
    void should_return_ticket_when_park_given_parking_lot_and_car_to_parking_bo() {

        // Given
        Car car = new Car();
        parkingLotList.add(new ParkingLot(1));
        //When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket_to_parking_bo() {
        // Given
        parkingLotList.add(new ParkingLot(1));
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //When
        Car actualCar = smartParkingBoy.fetch(parkingTicket);
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
        ParkingTicket bobParking = smartParkingBoy.park(carBob);
        ParkingTicket aliceParking = smartParkingBoy.park(carAlice);

        Car carOne = smartParkingBoy.fetch(bobParking);
        Car carTwo = smartParkingBoy.fetch(aliceParking);
        //Then
        assertEquals(carBob, carOne);
        assertEquals(carAlice, carTwo);
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_ticket_to_parking_bo() {
        // Given
        parkingLotList.add(new ParkingLot(1));
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //Then
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> smartParkingBoy.fetch(wrongParkingTicket));
        assertEquals("Unrecognized Parking Ticket " + wrongParkingTicket.hashCode()
                , runtimeException.getMessage());

    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_used_ticket_to_parking_boy() {
        parkingLotList.add(new ParkingLot(1));
        // Given
        ParkingTicket bobParking = smartParkingBoy.park(car);
        //When
        ParkingTicket usedTicket = new ParkingTicket();
        //Then

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> smartParkingBoy.fetch(usedTicket));
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
        smartParkingBoy.setParkingLot(parkingLotList);

        //when
        ParkingTicket parkingTix1 = smartParkingBoy.park(new Car());
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
        smartParkingBoy.setParkingLot(parkingLotList);
        //when
        ParkingTicket parkingTix1 = smartParkingBoy.park(carBob);
        ParkingTicket parkingTix2 = smartParkingBoy.park(carAlice);
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
        smartParkingBoy.setParkingLot(parkingLotList);
        //then

        Exception exception = assertThrows(Exception.class
                , () -> smartParkingBoy.park(carAlice));
        assertEquals("Not Enough Position", exception.getMessage());
    }

    @Test
    void should_return_park_lot_2_when_parking_given_parking_lot_1_is_has_more_than_lot_2_to_smart_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(7);
        ParkingLot parkinglot2 = new ParkingLot(2);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkinglot2);
        Car car1 = new Car();
        smartParkingBoy.park(car1);
        //when
        smartParkingBoy.findParkingLot();
        //then
        assertEquals(1, parkinglot2.getTicketAndCarMap().size());
    }
}
