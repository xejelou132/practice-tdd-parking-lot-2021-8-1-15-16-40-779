package com.parkinglot;

import java.util.List;

 public abstract class ParkingBoy {

    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car Car) {
        ParkingLot parkingLot = findParkingLot();
        return parkingLot.park(Car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchCar = new Car();
        for (ParkingLot parkingLot : parkingLotList) {
            fetchCar = parkingLot.fetch(parkingTicket);
        }
        return fetchCar;

    }

    public ParkingLot findParkingLot() {
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getCapacity() < 10)
                .findFirst()
                .orElseThrow(() -> new ParkingException("Not Enough Position"));
    }

    public void setParkingLot(List<ParkingLot> listParkingLots) {
        this.parkingLotList = listParkingLots;

    }

}
