import static org.junit.Assert.*;

import example.CalculatorArea;
import org.junit.Test;

public class CalculatorAreaTest {
    private final CalculatorArea calculator = new CalculatorArea();

    @Test
    public void testCalculateRectangleAreaWithIntegers() {
        String result = calculator.calculateRectangleArea("5", "7");
        assertTrue(result.contains("base: 5"));
        assertTrue(result.contains("altura: 7"));
        assertTrue(result.contains("Área calculada: 35"));
    }

    @Test
    public void testCalculateRectangleAreaWithDecimals() {
        String result = calculator.calculateRectangleArea("3.5", "2.5");
        assertTrue(result.contains("base: 3.5"));
        assertTrue(result.contains("altura: 2.5"));
        assertTrue(result.contains("Área calculada: 8.75"));
    }

    @Test
    public void testCalculateRectangleAreaWithCommaDecimals() {
        // Prueba con números que usan coma como separador decimal
        String result = calculator.calculateRectangleArea("2,5", "4,0");
        assertTrue(result.contains("base: 2.5"));
        assertTrue(result.contains("altura: 4.0") || result.contains("altura: 4"));
        assertTrue(result.contains("Área calculada: 10"));
    }

    @Test
    public void testCalculateRectangleAreaWithZeroValue() {
        String result = calculator.calculateRectangleArea("0", "5");
        assertTrue(result.contains("Error: La base y la altura deben ser valores positivos"));
    }

    @Test
    public void testCalculateRectangleAreaWithNegativeValue() {
        String result = calculator.calculateRectangleArea("-3", "2");
        assertTrue(result.contains("Error: La base y la altura deben ser valores positivos"));
    }

    @Test
    public void testCalculateRectangleAreaWithNonNumericInput() {
        String result = calculator.calculateRectangleArea("abc", "5");
        assertTrue(result.contains("Error: Ingrese valores numéricos válidos"));
    }

    @Test
    public void testCalculateRectangleAreaWithEmptyInput() {
        String result = calculator.calculateRectangleArea("", "5");
        assertTrue(result.contains("Error: Ingrese valores numéricos válidos"));
    }

    @Test
    public void testCalculateRectangleAreaWithNullInput() {
        String result = calculator.calculateRectangleArea(null, "5");
        assertTrue(result.contains("Error: Ingrese valores numéricos válidos"));
    }

    @Test
    public void testCalculateRectangleAreaWithVeryLargeNumbers() {
        String result = calculator.calculateRectangleArea("1000000", "2000000");
        assertTrue(result.contains("Área calculada: 2000000000000"));
    }

    @Test
    public void testCalculateRectangleAreaWithSmallDecimals() {
        String result = calculator.calculateRectangleArea("0.1", "0.1");
        assertTrue(result.contains("Área calculada: 0.01"));
    }
}