package pl.jpetryk.marsrover;

final class Rover {

  private Point point;
  private Direction direction;

  Rover(Point point, Direction direction) {
    this.point = point;
    this.direction = direction;
  }

  Point getCurrentPosition() {
    return point;
  }

  Direction getDirection() {
    return direction;
  }

  void moveForward() {
    switch (direction) {
    case NORTH:
      this.point = new Point(point.getX() + 1, point.getY());
      break;
    case EAST:
      this.point = new Point(point.getX(), point.getY() + 1);
      break;
    case SOUTH:
      this.point = new Point(point.getX() - 1, point.getY());
      break;
    case WEST:
      this.point = new Point(point.getX(), point.getY() - 1);
      break;
    }
  }

  void moveBackwards() {
    switch (direction) {
    case NORTH:
      this.point = new Point(point.getX() - 1, point.getY());
      break;
    case EAST:
      this.point = new Point(point.getX(), point.getY() - 1);
      break;
    case SOUTH:
      this.point = new Point(point.getX() + 1, point.getY());
      break;
    case WEST:
      this.point = new Point(point.getX(), point.getY() + 1);
      break;
    }
  }

  void rotateLeft() {
    this.direction = direction.left();
  }

  void rotateRight() {
    this.direction = direction.right();
  }

  @Override
  public String toString() {
    return "Rover(" + point.getX() + ", " + point.getY() + ", " + direction + ")";
  }

  String report() {
    return "(" + point.getX() + ", " + point.getY() + ") " + direction;
  }
}
