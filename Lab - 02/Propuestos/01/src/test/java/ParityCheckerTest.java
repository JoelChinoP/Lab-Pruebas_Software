import static org.junit.Assert.*;

import example.ParityChecker;
import org.junit.Test;

public class ParityCheckerTest {
    private final ParityChecker checker = new ParityChecker();

    @Test
    public void testCheckParityWithPositiveNumbers() {
        String result = checker.checkParity("3", new String[]{"1", "2", "3"});
        assertTrue(result.contains("1 es impar"));
        assertTrue(result.contains("2 es par"));
        assertTrue(result.contains("3 es impar"));
    }

    @Test
    public void testCheckParityWithZero() {
        String result = checker.checkParity("1", new String[]{"0"});
        assertTrue(result.contains("0 es par"));
    }

    @Test
    public void testCheckParityWithNegativeNumbers() {
        String result = checker.checkParity("3", new String[]{"-1", "-2", "-3"});
        assertTrue(result.contains("-1 es impar"));
        assertTrue(result.contains("-2 es par"));
        assertTrue(result.contains("-3 es impar"));
    }

    @Test
    public void testCheckParityWithMixedNumbers() {
        String result = checker.checkParity("4", new String[]{"0", "-5", "10", "21"});
        assertTrue(result.contains("0 es par"));
        assertTrue(result.contains("-5 es impar"));
        assertTrue(result.contains("10 es par"));
        assertTrue(result.contains("21 es impar"));
    }

    @Test
    public void testCheckParityWithInvalidCount() {
        String result = checker.checkParity("-1", new String[]{"1", "2"});
        assertTrue(result.contains("Error: La cantidad debe ser un número entero positivo"));
    }

    @Test
    public void testCheckParityWithZeroCount() {
        String result = checker.checkParity("0", new String[]{});
        assertTrue(result.contains("Error: La cantidad debe ser un número entero positivo"));
    }

    @Test
    public void testCheckParityWithNonNumericCount() {
        String result = checker.checkParity("abc", new String[]{"1", "2", "3"});
        assertTrue(result.contains("Error: 'abc' no es un número entero válido"));
    }

    @Test
    public void testCheckParityWithEmptyCount() {
        String result = checker.checkParity("", new String[]{"1", "2"});
        assertTrue(result.contains("Error: El valor no puede estar vacío"));
    }

    @Test
    public void testCheckParityWithNullCount() {
        String result = checker.checkParity(null, new String[]{"1", "2"});
        assertTrue(result.contains("Error: El valor no puede estar vacío"));
    }

    @Test
    public void testCheckParityWithNonNumericValues() {
        String result = checker.checkParity("3", new String[]{"1", "abc", "3"});
        assertTrue(result.contains("1 es impar"));
        assertTrue(result.contains("'abc' no es un número entero válido"));
        assertTrue(result.contains("3 es impar"));
    }

    @Test
    public void testCheckParityWithEmptyValues() {
        String result = checker.checkParity("2", new String[]{"1", ""});
        assertTrue(result.contains("1 es impar"));
        assertTrue(result.contains("'' no es un número entero válido"));
    }

    @Test
    public void testCheckParityWithNullValues() {
        String result = checker.checkParity("2", new String[]{"1", null});
        assertTrue(result.contains("1 es impar"));
        assertTrue(result.contains("'null' no es un número entero válido") ||
                result.contains("El valor no puede estar vacío"));
    }

    @Test
    public void testCheckParityWithMismatchedCount() {
        String result = checker.checkParity("5", new String[]{"1", "2", "3"});
        assertTrue(result.contains("Error: La cantidad de números proporcionados (3) no coincide con la cantidad especificada (5)"));
    }

    @Test
    public void testCheckParityWithNullArray() {
        String result = checker.checkParity("3", null);
        assertTrue(result.contains("Error: No se proporcionaron números para verificar"));
    }

    @Test
    public void testCheckParityWithLargeNumbers() {
        String result = checker.checkParity("2", new String[]{"2147483647", "-2147483648"});
        assertTrue(result.contains("2147483647 es impar"));
        assertTrue(result.contains("-2147483648 es par"));
    }
}