package pl.jpetryk.marsrover

import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class MarsRoverApplicationTest extends Specification {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream()

    @Delegate
    PollingConditions pollingConditions = new PollingConditions()

    def "should accept user input"() {
        given:
        def input = "3\n3\nNORTH\nFRFLF\n"
        def scanner = new Scanner(new ByteArrayInputStream(input.getBytes()))
        def inputParser = new InputParser(scanner, new PrintStream(outputStream))
        MarsRoverApplication application = new MarsRoverApplication(inputParser, new PrintStream(outputStream))

        when:
        application.run()

        then:
        outputStream.toString().split("\n").last() == "(5, 4) NORTH"
    }

    def "should prompt if user gives wrong position input"() {
        given:
        def scanner = new Scanner(new ByteArrayInputStream(givenInput.getBytes()))
        def inputParser = new InputParser(scanner, new PrintStream(outputStream))
        MarsRoverApplication application = new MarsRoverApplication(inputParser, new PrintStream(outputStream))

        when:
        Thread.start { application.run() }

        then:
        eventually {
            assert outputStream.toString().split("\n").contains(expectedMessage)
        }

        where:
        givenInput             | expectedMessage
        "asdasd\n"             | "Value has to be an integer."
        "3\nasd\n"             | "Value has to be an integer."
        "3\n3\nBRRRR\n"        | "Value has to be an of NORTH, SOUTH, EAST, WEST."
        "3\n3\nNORTH\nFFFSA\n" | "Value has to be an of (F, B, L, R)."

    }
}
