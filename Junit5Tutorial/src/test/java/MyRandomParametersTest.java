import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import RandomParametersExtension.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(RandomParametersExtension.class)

class MyRandomParametersTest {
    @Test
    void injectsInteger(@Random int i, @Random int J){
        assertNotEquals(i,j);
    }
}
