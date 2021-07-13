package com.shwetha;

import com.shwetha.commands.CommandExecutorFactory;
import com.shwetha.exception.InvalidModeException;
import com.shwetha.mode.FileMode;
import com.shwetha.mode.InteractiveMode;
import com.shwetha.service.ParkingLotService;
import java.io.IOException;

public class Main {
  public static void main(final String[] args) throws IOException {
    final OutputPrinter outputPrinter = new OutputPrinter();
    final ParkingLotService parkingLotService = new ParkingLotService();
    final CommandExecutorFactory commandExecutorFactory =
        new CommandExecutorFactory(parkingLotService);

    if (isInteractiveMode(args)) {
      new InteractiveMode(commandExecutorFactory, outputPrinter).process();
    } else if (isFileInputMode(args)) {
      new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
    } else {
      throw new InvalidModeException();
    }
  }

  /**
   * Checks whether the program is running using file input mode.
   *
   * @param args Command line arguments.
   * @return Boolean indicating whether in file input mode.
   */
  private static boolean isFileInputMode(final String[] args) {
    return args.length == 1;
  }

  
  private static boolean isInteractiveMode(final String[] args) {
    return args.length == 0;
  }
}
