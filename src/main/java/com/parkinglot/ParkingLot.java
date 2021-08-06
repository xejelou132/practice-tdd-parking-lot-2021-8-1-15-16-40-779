package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket , Car> parkedPositon = new HashMap();

    public ParkingTicket park(Car cars) {
        ParkingTicket parkingTicket = new ParkingTicket();
        if(parkedPositon.size()<=10) {
            parkedPositon.put(parkingTicket, cars);
            return parkingTicket;
        }
        return null;

    }

    public Car fetch(ParkingTicket parkingTicket) {
       Car carValue =  parkedPositon.get(parkingTicket);
        parkedPositon.remove(parkingTicket);
        return carValue;
    }


}
