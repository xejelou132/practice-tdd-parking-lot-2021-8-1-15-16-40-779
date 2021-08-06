package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    int capacity;

    private Map<ParkingTicket, Car> parkedPositon = new HashMap();
    public ParkingTicket park(Car cars) {

        ParkingTicket parkingTicket = new ParkingTicket();
        if (isNotFull()) {
            parkedPositon.put(parkingTicket, cars);
            return parkingTicket;
        }
        return null;
    }

    private boolean isNotFull() {
        return capacity < 11;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car carValue = parkedPositon.get(parkingTicket);
        parkedPositon.remove(parkingTicket);
        return carValue;
    }

    public ParkingLot(int capacitys) {
        this.capacity = capacitys;
    }
    public int getCapacity() {
        return capacity;
    }
}
