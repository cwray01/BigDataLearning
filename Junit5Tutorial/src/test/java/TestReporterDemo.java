import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.Map;

/**
 * RepetitionInfoParameterResolver
 * if a method parameter in a @RepeatedTest, @BeforeEach, or @AfterEach method
 * is of type RepetitionInfo, the RepetitionInfoParameterResolver will supply an instance of RepetitionInfo
 *
 * TestReporterParameterResolver:
 * if a method parameter is of type TestReporter,
 * the TestReporterParameterResolver will supplyy an instance of TestReporter.
 */
class TestReporterDemo {

    @Test
    void reportSingleValue(TestReporter testReporter){
        testReporter.publishEntry("a key", "a status message");
    }

    @Test
    void reportKeyValuePair(TestReporter testReporter){
        testReporter.publishEntry("a key", "a value");
    }

//    @Test
//    void reportMultipleKeyValuePairs(TestReporter testReporter){
//        testReporter.publishEntry(
//                Map.of(
//                        "user name", "dk38",
//                        "award year", "1974"
//                ));
//    }
}
