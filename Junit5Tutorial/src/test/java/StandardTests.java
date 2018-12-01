import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * A test method is any instatnce method that is
 * directly or metaannotated with
 * @Test, @RepeatedTest, @ ParameterizedTest, @TestFactory or @TestTemplate
 *
 * A test class is any top level or static member class that contains at least noe test method
 *
 * Note: Neither test classes nor test methods need to be pulic
 */
class StandardTests {
    @BeforeAll
    static void initAll(){
    }

    @BeforeEach
    void succeedingTest(){
    }

    @Test
    void falingTest(){
        fail("a falling test");
    }
    @Test
    @Disabled("for demonstration purposes")
    void skippedTest(){
        // not executed
    }

    @AfterEach
    void tearDown(){
    }

    @AfterAll
    static void tearDownAll(){
    }

}
