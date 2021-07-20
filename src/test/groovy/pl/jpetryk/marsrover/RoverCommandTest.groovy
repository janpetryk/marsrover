package pl.jpetryk.marsrover

import spock.lang.Specification

import static pl.jpetryk.marsrover.Direction.EAST
import static pl.jpetryk.marsrover.Direction.NORTH
import static pl.jpetryk.marsrover.Direction.WEST

class RoverCommandTest extends Specification {

    def "should move rover based on a command"() {
        given:
        def givenRover = new Rover(new Point(0, 0), NORTH)

        when:
        givenCommand.action.accept(givenRover)

        then:
        givenRover.currentPosition == expectedPosition
        givenRover.direction == expectedDirection

        where:
        givenCommand   | expectedPosition | expectedDirection
        RoverCommand.F | new Point(1, 0)  | NORTH
        RoverCommand.B | new Point(-1, 0) | NORTH
        RoverCommand.R | new Point(0, 0)  | EAST
        RoverCommand.L | new Point(0, 0)  | WEST
    }

}
