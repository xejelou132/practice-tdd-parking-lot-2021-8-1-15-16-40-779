package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity = 10;
    private Map<ParkingTicket, Car> parkCount = new HashMap<>();

    ParkingTicket park(Car vehicle) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkCount.put(parkingTicket, vehicle);
        return parkingTicket;
    }

     Car fetch(ParkingTicket parkingTicket) {
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


    public int getCapacity() {
        return capacity;
    }
    Map<ParkingTicket, Car> getTicketAndCarMap() {
        return parkCount;
    }




}
