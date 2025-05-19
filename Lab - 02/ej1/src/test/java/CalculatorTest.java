import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {
 private final Calculator calc = new Calculator();

 @Test
 public void testAddPositive() {
     assertEquals("5 + 7 debe ser 12", 12, calc.add(5, 7));
 }

 @Test
 public void testAddNegative() {
     assertEquals("-3 + 1 debe ser -2", -2, calc.add(-3, 1));
 }

 @Test
 public void testSubtract() {
     assertEquals("10 - 4 debe ser 6", 6, calc.subtract(10, 4));
 }

 @Test
 public void testSubtractNegativeResult() {
     assertEquals("2 - 5 debe ser -3", -3, calc.subtract(2, 5));
 }
}
