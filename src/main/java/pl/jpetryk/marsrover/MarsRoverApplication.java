package pl.jpetryk.marsrover;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class MarsRoverApplication {

  private final InputParser inputParser;
  private final PrintStream printStream;

  public MarsRoverApplication(InputParser inputParser, PrintStream printStream) {
    this.inputParser = inputParser;
    this.printStream = printStream;
  }

  public static void main(String[] args) {
    PrintStream printStream = System.out;
    Scanner scanner = new Scanner(System.in);
    var inputParser = new InputParser(scanner, printStream);
    var application = new MarsRoverApplication(inputParser, printStream);
    application.run();
  }

  void run() {
    int x = inputParser.parseInt("Enter X coordinate:");
    int y = inputParser.parseInt("Enter Y coordinate:");
    Direction direction = inputParser.parseDirection();

    Rover kata = new Rover(new Point(x, y), direction);

    List<Consumer<Rover>> parsedCommands = inputParser.parseCommands();
    parsedCommands.forEach(action -> action.accept(kata));
    printStream.print(kata.report());
  }

}
