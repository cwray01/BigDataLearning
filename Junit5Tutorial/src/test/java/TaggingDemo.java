import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test classes and methods can be tagged via the @Tag annotaion.
 * Those tags can later be used to filter test discovery and execution
 */

@Tag("fast")
@Tag("model")

class TaggingDemo {
    @Test
    @Tag("taxes")
    void testingTaxCalculation(){

    }
}
