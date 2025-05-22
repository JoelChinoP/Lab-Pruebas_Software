import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.arrayContaining;

import example.ParityChecker;
import org.junit.Test;

public class ParityCheckerTest {
    private final ParityChecker checker = new ParityChecker();

    // Función utilitaria para convertir boolean[] a Boolean[]
    private Boolean[] toObjectArray(boolean[] primitiveArray) {
        Boolean[] result = new Boolean[primitiveArray.length];
        for (int i = 0; i < primitiveArray.length; i++) {
            result[i] = primitiveArray[i];
        }
        return result;
    }

    @Test
    public void whenCheckingPositiveNumbers_thenReturnCorrectParity() {
        boolean[] result = checker.checkParity("3", new String[]{"1", "2", "3"});
        assertThat("Debe retornar array con paridad correcta",
                toObjectArray(result), is(arrayContaining(false, true, false)));
    }

    @Test
    public void whenCheckingZero_thenReturnTrue() {
        boolean[] result = checker.checkParity("1", new String[]{"0"});
        assertThat("0 debe ser par", result[0], is(true));
    }

    @Test
    public void whenCheckingNegativeNumbers_thenReturnCorrectParity() {
        boolean[] result = checker.checkParity("3", new String[]{"-1", "-2", "-3"});
        assertThat("Debe retornar array con paridad correcta para negativos",
                toObjectArray(result), is(arrayContaining(false, true, false)));
    }

    @Test
    public void whenCheckingMixedNumbers_thenReturnCorrectParity() {
        boolean[] result = checker.checkParity("4", new String[]{"0", "-5", "10", "21"});
        assertThat("Debe retornar array con paridad correcta para números mixtos",
                toObjectArray(result), is(arrayContaining(true, false, true, false)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCountIsNegative_thenThrowIllegalArgumentException() {
        checker.checkParity("-1", new String[]{"1", "2"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCountIsZero_thenThrowIllegalArgumentException() {
        checker.checkParity("0", new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCountIsNonNumeric_thenThrowIllegalArgumentException() {
        checker.checkParity("abc", new String[]{"1", "2", "3"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCountIsEmpty_thenThrowIllegalArgumentException() {
        checker.checkParity("", new String[]{"1", "2"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCountIsNull_thenThrowIllegalArgumentException() {
        checker.checkParity(null, new String[]{"1", "2"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValuesContainNonNumeric_thenThrowIllegalArgumentException() {
        checker.checkParity("3", new String[]{"1", "abc", "3"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValuesContainEmpty_thenThrowIllegalArgumentException() {
        checker.checkParity("2", new String[]{"1", ""});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValuesContainNull_thenThrowIllegalArgumentException() {
        checker.checkParity("2", new String[]{"1", null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCountMismatch_thenThrowIllegalArgumentException() {
        checker.checkParity("5", new String[]{"1", "2", "3"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArrayIsNull_thenThrowIllegalArgumentException() {
        checker.checkParity("3", null);
    }

    @Test
    public void whenCheckingLargeNumbers_thenReturnCorrectParity() {
        boolean[] result = checker.checkParity("2", new String[]{"2147483647", "-2147483648"});
        assertThat("Debe manejar números grandes correctamente",
                toObjectArray(result), is(arrayContaining(false, true)));
    }

    @Test
    public void checkerInstanceShouldBeOfTypeParityChecker() {
        assertThat("La instancia debe ser de tipo ParityChecker",
                checker, instanceOf(ParityChecker.class));
    }

    @Test
    public void whenCheckingValidNumbers_thenResultShouldNotBeNull() {
        boolean[] result = checker.checkParity("2", new String[]{"4", "6"});
        assertThat("El resultado no debe ser null", result, notNullValue());
        assertThat("El resultado debe tener longitud 2", result.length, is(2));
        assertThat("Ambos números deben ser pares", toObjectArray(result), is(arrayContaining(true, true)));
    }
}
