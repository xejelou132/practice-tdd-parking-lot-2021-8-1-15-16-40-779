package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoytest {
    private Car car;
    private List<ParkingLot> parkingLotList;
    private SuperSmartParkingBoy superSmartParkingBoy;

    @BeforeEach
    void setUp() {
        car = new Car();
        parkingLotList = new ArrayList<>();
        superSmartParkingBoy = new SuperSmartParkingBoy (parkingLotList);
    }
    @Test
    void should_return_ticket_when_park_given_parking_lot_and_car_to_parking_bo() {

        // Given
        Car car = new Car();
        parkingLotList.add(new ParkingLot(1));
        //When
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        //Then
        assertNotNull(parkingTicket);

    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket_to_parking_bo() {
        // Given
        parkingLotList.add(new ParkingLot(1));
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        //When
        Car actualCar = superSmartParkingBoy.fetch(parkingTicket);
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
        ParkingTicket bobParking = superSmartParkingBoy.park(carBob);
        ParkingTicket aliceParking = superSmartParkingBoy.park(carAlice);

        Car carOne = superSmartParkingBoy.fetch(bobParking);
        Car carTwo = superSmartParkingBoy.fetch(aliceParking);
        //Then
        assertEquals(carBob, carOne);
        assertEquals(carAlice, carTwo);
    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_wrong_ticket_to_parking_bo() {
        // Given
        parkingLotList.add(new ParkingLot(1));
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        //Then
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> superSmartParkingBoy.fetch(wrongParkingTicket));
        assertEquals("Unrecognized Parking Ticket " + wrongParkingTicket.hashCode()
                , runtimeException.getMessage());

    }

    @Test
    void should_return_nothing_when_fetch_given_parking_lot_and_used_ticket_to_parking_boy() {
        parkingLotList.add(new ParkingLot(1));
        // Given
        ParkingTicket bobParking = superSmartParkingBoy.park(car);
        //When
        ParkingTicket usedTicket = new ParkingTicket();
        //Then

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> superSmartParkingBoy.fetch(usedTicket));
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
        superSmartParkingBoy.setParkingLot(parkingLotList);

        //when
        ParkingTicket parkingTix1 = superSmartParkingBoy.park(new Car());
        //then
        assertNotNull(parkingTix1);
    }

    @Test
    void should_return_car_ticket_to_lot_2_when_parking_given_car_while_parking_lot_1_is_at_max_capacity_to_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(9);
        ParkingLot parkinglot2 = new ParkingLot(1);
        Car carBob = new Car();
        Car carAlice = new Car();
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkinglot2);
        superSmartParkingBoy.setParkingLot(parkingLotList);
        //when
        ParkingTicket parkingTix1 = superSmartParkingBoy.park(carBob);
        ParkingTicket parkingTix2 = superSmartParkingBoy.park(carAlice);
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
        superSmartParkingBoy.setParkingLot(parkingLotList);
        //then

        Exception exception = assertThrows(Exception.class
                , () -> superSmartParkingBoy.park(carAlice));
        assertEquals("Not Enough Position", exception.getMessage());
    }

    @Test
    void should_return_lot_1_when_parking_given_2_parking_lot_base_on_rate_to_super_smart_parking_boy() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkinglot2 = new ParkingLot(3);
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkinglot2);
        Car car1 = new Car();
        superSmartParkingBoy.park(car1);
        //when
        superSmartParkingBoy.findParkingLot();
        //then
        assertNotNull(parkinglot2);
    }
}
