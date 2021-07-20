package pl.jpetryk.marsrover;

import java.util.Arrays;
import java.util.List;

enum Direction {

  NORTH, EAST, SOUTH, WEST;

  Direction left() {
    List<Direction> directions = Arrays.asList(values());
    int leftDirectionIndex = (directions.indexOf(this) + directions.size() - 1) % directions.size();
    return directions.get(leftDirectionIndex);
  }

  Direction right() {
    List<Direction> directions = Arrays.asList(values());
    int rightDirectionIndex = (directions.indexOf(this) + 1) % directions.size();
    return directions.get(rightDirectionIndex);
  }

}
