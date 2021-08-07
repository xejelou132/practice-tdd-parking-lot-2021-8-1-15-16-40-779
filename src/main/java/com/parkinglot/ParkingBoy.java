package com.parkinglot;

import java.util.List;

public class ParkingBoy {
    ParkingLot parkingLot;
    Car fetchVehicle = new Car();

    public ParkingBoy(ParkingLot parkingLotList) {
        this.parkingLot = parkingLotList;
    }

    public ParkingTicket park(Car vehicle) {
        return parkingLot.park(vehicle);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        fetchVehicle = parkingLot.fetch(parkingTicket);
        return fetchVehicle;

    }

}
