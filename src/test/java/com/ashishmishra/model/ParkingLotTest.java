package com.ashishmishra.model;

import static org.junit.Assert.*;

import com.ashishmishra.exception.InvalidSlotException;
import com.ashishmishra.exception.ParkingLotException;
import com.ashishmishra.exception.SlotAlreadyOccupiedException;
import org.junit.Test;

public class ParkingLotTest {

  @Test(expected = ParkingLotException.class)
  public void testNegativeCapacity() {
    new ParkingLot(-1);
  }

  @Test(expected = ParkingLotException.class)
  public void testZeroCapacity() {
    new ParkingLot(0);
  }

  @Test
  public void testValidCapacity() {
    new ParkingLot(100);
  }

  @Test(expected = ParkingLotException.class)
  public void testMoreThanMaxCapacity() {
    new ParkingLot(1000001);
  }

  @Test
  public void testParkingCar() {
    final Car testCar = new Car("test-car-no", "white");
    final ParkingLot parkingLot = new ParkingLot(100);
    final Slot slot = parkingLot.park(testCar, 1);
    assertEquals(testCar, slot.getParkedCar());
  }

  @Test(expected = SlotAlreadyOccupiedException.class)
  public void testParkingOnAlreadyOccupiedSlot() {
    final Car testCar1 = new Car("test-car-no1", "white");
    final Car testCar2 = new Car("test-car-no2", "blue");
    final ParkingLot parkingLot = new ParkingLot(100);
    parkingLot.park(testCar1, 1);
    parkingLot.park(testCar2, 1);
  }

  @Test(expected = InvalidSlotException.class)
  public void testParkingAtSlotHigherThanCapacity() {
    final Car testCar = new Car("test-car-no", "white");
    final ParkingLot parkingLot = new ParkingLot(100);
    parkingLot.park(testCar, 101);
  }

  @Test(expected = InvalidSlotException.class)
  public void testParkingAtSlotInvalidSlot() {
    final Car testCar = new Car("test-car-no", "white");
    final ParkingLot parkingLot = new ParkingLot(100);
    parkingLot.park(testCar, 0);
  }

  @Test
  public void testMakingSlotFree() {
    final Car testCar = new Car("test-car-no", "white");
    final ParkingLot parkingLot = new ParkingLot(100);

    final Slot parkedSlot = parkingLot.park(testCar, 10);
    assertFalse(parkedSlot.isSlotFree());

    final Slot freedSlot = parkingLot.makeSlotFree(10);
    assertTrue(freedSlot.isSlotFree());
  }

  @Test(expected = InvalidSlotException.class)
  public void testMakingSlotHigherThanCapacityFree() {
    final ParkingLot parkingLot = new ParkingLot(100);
    parkingLot.makeSlotFree(101);
  }

  @Test(expected = InvalidSlotException.class)
  public void testMakingInvalidSlotFree() {
    final ParkingLot parkingLot = new ParkingLot(100);
    parkingLot.makeSlotFree(-1);
  }
}
