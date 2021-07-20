package pl.jpetryk.marsrover;

import java.util.function.Consumer;

enum RoverCommand {

  F(Rover::moveForward), B(Rover::moveBackwards), L(Rover::rotateLeft), R(Rover::rotateRight);

  private Consumer<Rover> action;

  RoverCommand(Consumer<Rover> action) {
    this.action = action;
  }

  Consumer<Rover> getAction() {
    return action;
  }
}
