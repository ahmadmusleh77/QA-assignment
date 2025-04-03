package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Calculator;


@DisplayName("calculator tests")
@Execution(value = ExecutionMode.CONCURRENT)
class CalculatorTest {

Calculator calc;


@BeforeAll
   static void setUpBeforeClass() {
   System.out.println("starting calculator tests");
   }

	
@BeforeEach
void setUp() throws Exception{
	
calc = new Calculator();
System.out.println("calculator initialized");
}



@Test
@DisplayName("test addition with valid inputs")
@Order(1)
void testAddValidInputs() {
    assertEquals(3, calc.add(1, 2));
    assertEquals(0, calc.add(-5, 5));
    assertEquals(10, calc.add(1, 2, 3, 4));
}



@ParameterizedTest
@CsvSource({"1, 2, 3",
            "-5, 5, 0",
             "3, 3, 6"
           })
@DisplayName("parameterized test")
@Order(2)
void parameterizedTest(int a, int b, int expected) {
    assertEquals(expected, calc.add(a, b));
}


@Test
@DisplayName("test division with valid input")
@Order(3)
void testDivideValid() {
    assertEquals(5, calc.divide(10, 2));
}



@Test
@DisplayName("test division by zero")
@Order(4)
void testDivideByZero() {
    assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
}


@Test
@DisplayName("test factorial with valid input")
@Order(5)
void testFactorialValid() {
    assertEquals(120, calc.factorial(5));
    assertEquals(1, calc.factorial(0));
}


@Test
@DisplayName("test factorial with negative input")
@Order(6)
void testFactorialNegative() {
    assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
}


@Test
@DisplayName("timeout test factorial")
@Timeout(100)
@Order(7)
void timeoutFactorial() {
    assertEquals(1, calc.factorial(0));
}

@Test
@DisplayName("intentionally failing test")
@Disabled("this test fails because factorial(6) is 720 not 120")
@Order(8)
void failingTest(){
    assertEquals(120, calc.factorial(6));
    //true ==>  assertEquals(720, calc.factorial(6));
}
@AfterEach
void postTest() {
    System.out.println("post test calculator test");
}

@AfterAll
static void afterAll() {
    System.out.println("after all calculator tests");
}

}


