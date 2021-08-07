package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket, Car> parkCount = new HashMap();
    public ParkingTicket park(Car cars) {
        if (checkParkCount()){
            ParkingTicket parkingTicket = new ParkingTicket();
            parkCount.put(parkingTicket, cars);
            return parkingTicket;
        }
       return null;

    }

    private boolean checkParkCount() {
        return parkCount.size() >= 10;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car carCount = parkCount.get(parkingTicket);
        parkCount.remove(parkingTicket);
        return carCount;
    }

}
