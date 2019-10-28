package com.uditagarwal.commands;

import static com.uditagarwal.commands.CommandConstants.CREATE_PARKING_LOT;

import com.uditagarwal.exception.InvalidCommandException;
import com.uditagarwal.model.Command;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory to get correct {@link CommandExecutor} from a given command.
 */
public class CommandExecutorFactory {
  Map<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

  public CommandExecutorFactory() {
    commands.put(CREATE_PARKING_LOT, new CreateParkingLotCommandExecutor());
  }

  public CommandExecutor getCommandExecutor(final Command command) {
    CommandExecutor commandExecutor = commands.get(command.getCommandName());
    if (command == null) {
      throw new InvalidCommandException();
    }
    return commandExecutor;
  }
}