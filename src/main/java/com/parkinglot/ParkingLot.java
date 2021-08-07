package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket, Car> parkCount = new HashMap();
    public ParkingTicket park(Car cars) {
        if (checkParkCount()){
            throw new ParkingException("No available Position");
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkCount.put(parkingTicket, cars);
        return parkingTicket;
    }

    private boolean checkParkCount() {
        return parkCount.size() > 10;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (isUnrecognizedParkingTicket(parkingTicket)) {
            throw new ParkingException("Unrecognized Parking Ticket " + parkingTicket.hashCode());
        }
        Car carCount = parkCount.get(parkingTicket);
        parkCount.remove(parkingTicket);
        return carCount;
    }

    private boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
       return !parkCount.containsKey(parkingTicket);
    }

}
