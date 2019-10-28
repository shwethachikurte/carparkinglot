package com.uditagarwal.service;

import com.uditagarwal.exception.ParkingLotException;
import com.uditagarwal.model.Car;
import com.uditagarwal.model.ParkingLot;
import com.uditagarwal.model.parking.strategy.ParkingStrategy;

public class ParkingLotService {
  private ParkingLot parkingLot;
  private ParkingStrategy parkingStrategy;

  public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy) {
    if (this.parkingLot != null) {
      throw new ParkingLotException("Parking lot already exists.");
    }
    this.parkingLot = parkingLot;
    this.parkingStrategy = parkingStrategy;
    for (int i = 1; i <= parkingLot.getCapacity(); i++) {
      parkingStrategy.addSlot(i);
    }
  }

  public Integer park(final Car car) {
    validateParkingLotExists();
    final Integer nextFreeSlot = parkingStrategy.getNextSlot();
    parkingLot.park(car, nextFreeSlot);
    parkingStrategy.removeSlot(nextFreeSlot);
    return nextFreeSlot;
  }

  public void makeSlotFree(final Integer slotNumber) {
    validateParkingLotExists();
    parkingLot.makeSlotFree(slotNumber);
    parkingStrategy.addSlot(slotNumber);
  }

  private void validateParkingLotExists() {
    if (parkingLot == null) {
      throw new ParkingLotException("Parking lot does not exists to park.");
    }
  }
}