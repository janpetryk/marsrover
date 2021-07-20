package pl.jpetryk.marsrover;

import static java.lang.System.lineSeparator;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MarsRoverApplication {

  private final Scanner scanner;
  private final PrintStream printStream;

  public MarsRoverApplication(Scanner scanner, PrintStream printStream) {
    this.scanner = scanner;
    this.printStream = printStream;
  }

  public static void main(String[] args) {
    var application = new MarsRoverApplication(new Scanner(System.in), System.out);
    application.run();
  }

  void run() {
    int x = parseInt("Enter X coordinate:");
    int y = parseInt("Enter Y coordinate:");
    Direction direction = parseDirection();

    Rover kata = new Rover(new Point(x, y), direction);

    List<Consumer<Rover>> parsedCommands = parseCommands();
    parsedCommands.forEach(action -> action.accept(kata));
    printStream.print(kata.report());
    printStream.print(lineSeparator());
  }

  private int parseInt(String message) {
    Integer result = null;

    while (result == null) {
      printStream.print(message);
      printStream.print(lineSeparator());
      try {
        result = scanner.nextInt();
      } catch (NoSuchElementException e) {
        scanner.nextLine();
        printStream.print("Value has to be an integer.");
        printStream.print(lineSeparator());
      }
    }
    return result;
  }


  private Direction parseDirection() {
    Direction result = null;

    while (result == null) {
      printStream.print("Enter direction robot is facing (NORTH, SOUTH, EAST, WEST):");
      printStream.print(lineSeparator());
      try {
        result = Direction.valueOf(scanner.next());
      } catch (IllegalArgumentException e) {
        scanner.nextLine();
        printStream.print("Value has to be an of NORTH, SOUTH, EAST, WEST.");
        printStream.print(lineSeparator());
      }
    }

    return result;
  }

  private List<Consumer<Rover>> parseCommands() {
    List<Consumer<Rover>> result = null;

    while (result == null) {
      printStream.print("Enter commands (F, B, L, R):");
      printStream.print(lineSeparator());
      try {

        String commands = scanner.next();
        result = Arrays.stream(commands.split(""))
            .map(String::toUpperCase)
            .map(RoverCommand::valueOf)
            .map(RoverCommand::getAction)
            .collect(Collectors.toList());
      } catch (IllegalArgumentException e) {
        scanner.nextLine();
        printStream.print("Value has to be an of (F, B, L, R).");
        printStream.print(lineSeparator());
      }
    }
    return result;
  }

}