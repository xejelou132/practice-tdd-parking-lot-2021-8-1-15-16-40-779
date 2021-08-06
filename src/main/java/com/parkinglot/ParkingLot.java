package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket , Car> parkedPositon = new HashMap();

    public ParkingTicket park(Car cars) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPositon.put(parkingTicket , cars);

        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkedPositon.get(parkingTicket);
    }
}
