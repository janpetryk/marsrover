package pl.jpetryk.marsrover;

import static java.lang.System.lineSeparator;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class InputParser {

  private final Scanner scanner;
  private final PrintStream printStream;

  InputParser(Scanner scanner, PrintStream printStream) {
    this.scanner = scanner;
    this.printStream = printStream;
  }

  int parseInt(String message) {
    Integer result = null;

    while (result == null) {
      printWithNewLine(message);
      try {
        result = scanner.nextInt();
      } catch (NoSuchElementException e) {
        scanner.nextLine();
        printWithNewLine("Value has to be an integer.");
      }
    }
    return result;
  }


  Direction parseDirection() {
    Direction result = null;

    while (result == null) {
      printWithNewLine("Enter direction robot is facing (NORTH, SOUTH, EAST, WEST):");
      try {
        result = Direction.valueOf(scanner.next());
      } catch (IllegalArgumentException e) {
        scanner.nextLine();
        printWithNewLine("Value has to be an of NORTH, SOUTH, EAST, WEST.");
      }
    }

    return result;
  }

  List<Consumer<Rover>> parseCommands() {
    List<Consumer<Rover>> result = null;

    while (result == null) {
      printWithNewLine("Enter commands (F, B, L, R):");
      try {

        String commands = scanner.next();
        result = Arrays.stream(commands.split(""))
            .map(String::toUpperCase)
            .map(RoverCommand::valueOf)
            .map(RoverCommand::getAction)
            .collect(Collectors.toList());
      } catch (IllegalArgumentException e) {
        scanner.nextLine();
        printWithNewLine("Value has to be an of (F, B, L, R).");
      }
    }
    return result;
  }

  private void printWithNewLine(String s) {
    printStream.print(s);
    printStream.print(lineSeparator());
  }
}
