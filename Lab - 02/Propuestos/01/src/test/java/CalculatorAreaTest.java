import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.closeTo;
import example.CalculatorArea;
import org.junit.Test;

public class CalculatorAreaTest {
    private final CalculatorArea calculator = new CalculatorArea();

    @Test
    public void whenCalculatingWithIntegers_thenReturnCorrectArea() {
        double result = calculator.calculateRectangleArea("5", "7");
        assertThat("El área debe ser 35", result, is(35.0));
    }

    @Test
    public void whenCalculatingWithDecimals_thenReturnCorrectArea() {
        double result = calculator.calculateRectangleArea("3.5", "2.5");
        assertThat("El área debe ser 8.75", result, is(8.75));
    }

    @Test
    public void whenCalculatingWithCommaDecimals_thenReturnCorrectArea() {
        double result = calculator.calculateRectangleArea("2,5", "4,0");
        assertThat("El área debe ser 10.0", result, is(10.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBaseIsZero_thenThrowIllegalArgumentException() {
        calculator.calculateRectangleArea("0", "5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenHeightIsNegative_thenThrowIllegalArgumentException() {
        calculator.calculateRectangleArea("-3", "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputIsNonNumeric_thenThrowIllegalArgumentException() {
        calculator.calculateRectangleArea("abc", "5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputIsEmpty_thenThrowIllegalArgumentException() {
        calculator.calculateRectangleArea("", "5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInputIsNull_thenThrowIllegalArgumentException() {
        calculator.calculateRectangleArea(null, "5");
    }

    @Test
    public void whenCalculatingWithVeryLargeNumbers_thenReturnCorrectArea() {
        double result = calculator.calculateRectangleArea("1000000", "2000000");
        assertThat("El área debe ser 2000000000000", result, is(2000000000000.0));
    }

    @Test
    public void whenCalculatingWithSmallDecimals_thenReturnCorrectArea() {
        double result = calculator.calculateRectangleArea("0.1", "0.1");
        // Usar closeTo para números decimales pequeños debido a precisión floating point
        assertThat("El área debe ser aproximadamente 0.01", result, is(closeTo(0.01, 0.0001)));
    }

    @Test
    public void whenCalculatingWithMixedFormats_thenReturnCorrectArea() {
        double result = calculator.calculateRectangleArea("10,5", "2.0");
        assertThat("El área debe ser 21.0", result, is(21.0));
    }

    @Test
    public void calculatorInstanceShouldBeOfTypeCalculatorArea() {
        assertThat("La instancia debe ser de tipo CalculatorArea",
                calculator, instanceOf(CalculatorArea.class));
    }

    @Test
    public void whenCalculatingValidArea_thenResultShouldNotBeNull() {
        double result = calculator.calculateRectangleArea("4", "6");
        assertThat("El resultado no debe ser null", result, notNullValue());
        assertThat("El resultado debe ser mayor que 0", result, is(24.0));
    }
}