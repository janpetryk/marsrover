package pl.jpetryk.marsrover

import spock.lang.Specification
import spock.lang.Unroll

import static pl.jpetryk.marsrover.Direction.EAST
import static pl.jpetryk.marsrover.Direction.NORTH
import static pl.jpetryk.marsrover.Direction.SOUTH
import static pl.jpetryk.marsrover.Direction.WEST

class DirectionTest extends Specification {


    @Unroll
    def "should provide a proper left and right direction"() {
        expect:
        givenDirection.left() == expectedLeft
        givenDirection.right() == expectedRight

        where:
        givenDirection | expectedLeft | expectedRight
        NORTH          | WEST         | EAST
        EAST           | NORTH        | SOUTH
        SOUTH          | EAST         | WEST
        WEST           | SOUTH        | NORTH
    }
}
