package com.ashishmishra.commands;

import com.ashishmishra.OutputPrinter;
import com.ashishmishra.model.Command;
import com.ashishmishra.model.Slot;
import com.ashishmishra.service.ParkingLotService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Executor to handle command of fetching all slot numbers in which cars of a particular color are
 * parked.
 */
public class ColorToSlotNumberCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

  public ColorToSlotNumberCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final Command command) {
    final List<Slot> slotsForColor = parkingLotService.getSlotsForColor(command.getParams().get(0));
    if (slotsForColor.isEmpty()) {
      outputPrinter.notFound();
    } else {
      final String result =
          slotsForColor.stream()
              .map(slot -> slot.getSlotNumber().toString())
              .collect(Collectors.joining(", "));
      outputPrinter.printWithNewLine(result);
    }
  }
}
