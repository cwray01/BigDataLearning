import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 * Third party Assertion Lib
 */
class HamcrestAssertionDemo {
    @Test
    void assertWithHamcrestMatcher(){
        assertThat(2 + 1, is(equalTo(3)));
    }
}
