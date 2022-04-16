package com.ashishmishra.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.ashishmishra.OutputPrinter;
import com.ashishmishra.model.Command;
import com.ashishmishra.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;

public class LeaveCommandExecutorTest {

  private ParkingLotService parkingLotService;
  private OutputPrinter outputPrinter;
  private LeaveCommandExecutor leaveCommandExecutor;

  @Before
  public void setUp() throws Exception {
    parkingLotService = mock(ParkingLotService.class);
    outputPrinter = mock(OutputPrinter.class);
    leaveCommandExecutor = new LeaveCommandExecutor(parkingLotService, outputPrinter);
  }

  @Test
  public void testValidCommand() {
    assertTrue(leaveCommandExecutor.validate(new Command("leave 1")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(leaveCommandExecutor.validate(new Command("leave")));
    assertFalse(leaveCommandExecutor.validate(new Command("leave 1 2")));
    assertFalse(leaveCommandExecutor.validate(new Command("leave 1 a")));
    assertFalse(leaveCommandExecutor.validate(new Command("leave abcd")));
  }

  @Test
  public void testLeavingCarFromSlotMakesSlotFreeFromParkingService() {
    leaveCommandExecutor.execute(new Command("leave 1"));
    verify(parkingLotService).makeSlotFree(1);
    verify(outputPrinter).printWithNewLine("Slot number 1 is free");
  }
}
