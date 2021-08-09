package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot findParkingLot() {
        return  parkingLotList.stream()
                .min(Comparator.comparing(ParkingLot::getEmptySpace))
                .orElseThrow(() -> new ParkingException("Not Enough Position"));
    }
}
