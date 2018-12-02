import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DisabledTestDemo {
    @Disabled
    @Test
    void testWillBeSkipped(){

    }

    @Test
    void testWillBeExecuted(){

    }
}
