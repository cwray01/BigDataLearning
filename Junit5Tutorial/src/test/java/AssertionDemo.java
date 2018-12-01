import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * JUnit Jupiter comes with many of the assertion methods
 * that JUnit4 has adds a few that lend themselves well to being
 * used with Java 8 lambdas. All JUnit Jupiter assertion are static methods
 * in the org.junit.jupiter.apit.Assertions calss
 */
class AssertionDemo {
    @BeforeAll

    @Test
    void standardAssertions(){
        assertEquals(2,2);
        assertEquals(4, 4, "The optional assertion message is now the laster perameter.");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated --"
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions(){
        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together
        assertAll("person",
                () -> assertEquals("John", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
        
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("n"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }
}
