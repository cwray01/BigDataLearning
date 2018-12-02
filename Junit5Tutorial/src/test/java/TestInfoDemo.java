import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * [ParameterResolver] defines the API for test extensions
 * that wish to dynamically resolve parameters at runtime.
 * If a test constructor or a @Test @TestFactory, @BeforeEach, @AfterEach, @BeforeAll, of @AfterAll
 * method accepts a parameter, the parameter must be resolved at runtime by a registered ParameterResolver
 */

/**
 * [TestInfoParameterResolver] if a method parameter is of type TestInfo
 * the TestInfoParameterResolver will supply an instance of TestInfo corresponding to the current test
 * as the value for the parameter
 */

/**
 * [TestInfo] acts as a drop-in replacement for the TestName rule from Junit4
 * The following demonstrates how to have TestInfo injected into a test constructor,
 * @BeforeEach method and @Test method
 */

@DisplayName("TestInfo Demo")

class TestInfoDemo {

    TestInfoDemo(TestInfo testInfo){
        assertEquals("TestInfo Demo", testInfo.getDisplayName());
    }

    @BeforeEach
    void init(TestInfo testInfo){
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("TEST 1") ||
                displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo){
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    void test2(){

    }
}
