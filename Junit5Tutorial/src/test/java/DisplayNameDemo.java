import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test classes and test methods can declare custom display names
 * with sapaces, special characters, and even emojis
 */

@DisplayName("A spectial test case")
class DisplayNameDemo {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces(){
    }

    @Test
    @DisplayName("(╯‵□′)╯︵┻━┻")
    void testZWithDisplayNameContainingSpecialCharacters()
    {
    }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji(){

    }
}
