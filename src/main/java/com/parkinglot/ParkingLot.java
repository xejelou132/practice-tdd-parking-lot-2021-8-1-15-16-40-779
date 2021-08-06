package com.parkinglot;

public class ParkingLot {
    private Car car;

    public ParkingTicket park(Car cars) {
        this.car = cars;
        ParkingTicket parkingTicket = new ParkingTicket();

        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return car;
    }
}
