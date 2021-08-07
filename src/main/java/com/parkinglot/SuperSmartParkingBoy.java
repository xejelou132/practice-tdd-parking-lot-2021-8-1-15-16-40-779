package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot findParkingLot() {
        return  parkingLotList.stream()
                .min(Comparator.comparing(ParkingLot::getRate))
                .orElseThrow(() ->  new ParkingException("Not Enough Position"));
    }


}
