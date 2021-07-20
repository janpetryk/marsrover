package pl.jpetryk.marsrover

import spock.lang.Specification

import static pl.jpetryk.marsrover.Direction.EAST
import static pl.jpetryk.marsrover.Direction.NORTH
import static pl.jpetryk.marsrover.Direction.SOUTH
import static pl.jpetryk.marsrover.Direction.WEST

class RoverTest extends Specification {

    def "should move the rover forward"() {
        when:
        givenRover.moveForward()

        then:
        givenRover.currentPosition == expectedPoint

        where:
        givenRover                        | expectedPoint
        new Rover(new Point(0, 0), NORTH) | new Point(1, 0)
        new Rover(new Point(0, 0), SOUTH) | new Point(-1, 0)
        new Rover(new Point(0, 0), WEST)  | new Point(0, -1)
        new Rover(new Point(0, 0), EAST)  | new Point(0, 1)
    }

    def "should move the rover backwards"() {
        when:
        givenRover.moveBackwards()

        then:
        givenRover.currentPosition == expectedPoint

        where:
        givenRover                        | expectedPoint
        new Rover(new Point(0, 0), NORTH) | new Point(-1, 0)
        new Rover(new Point(0, 0), SOUTH) | new Point(1, 0)
        new Rover(new Point(0, 0), WEST)  | new Point(0, 1)
        new Rover(new Point(0, 0), EAST)  | new Point(0, -1)
    }

    def "should rotate rover left"() {
        when:
        givenRover.rotateLeft()

        then:
        givenRover.direction == expectedDirection

        where:
        givenRover                        | expectedDirection
        new Rover(new Point(0, 0), NORTH) | WEST
        new Rover(new Point(0, 0), SOUTH) | EAST
        new Rover(new Point(0, 0), WEST)  | SOUTH
        new Rover(new Point(0, 0), EAST)  | NORTH
    }

    def "should rotate rover right"() {
        when:
        givenRover.rotateRight()

        then:
        givenRover.direction == expectedDirection

        where:
        givenRover                        | expectedDirection
        new Rover(new Point(0, 0), NORTH) | EAST
        new Rover(new Point(0, 0), SOUTH) | WEST
        new Rover(new Point(0, 0), WEST)  | NORTH
        new Rover(new Point(0, 0), EAST)  | SOUTH
    }

    def "should move to a specific point"() {
        given:
        def givenRover = new Rover(new Point(0, 0), NORTH)

        when:
        (1..10).forEach {givenRover.moveForward()}
        givenRover.rotateRight()
        (1..10).forEach {givenRover.moveForward()}

        then:
        givenRover.currentPosition == new Point(10, 10)
        givenRover.direction == EAST

    }


}
