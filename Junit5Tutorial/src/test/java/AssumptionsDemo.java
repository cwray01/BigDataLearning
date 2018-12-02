import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;
class AssumptionsDemo {

    @Test
    void testOnlyOnCiServer(){
        assumeTrue("CI".equals(System.getenv("ENV")));
        //remainder of test
    }

    @Test
    void testOnlyDeveloperWorkstation(){
        System.out.println(System.getenv("ENV"));
        assumeTrue("null".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironment(){
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
            // perform these assertions only on the CI server
                    assertEquals(2,2);
                });
        //perform these assertions in all environments
        assertEquals("a string", "a string");

    }
}
