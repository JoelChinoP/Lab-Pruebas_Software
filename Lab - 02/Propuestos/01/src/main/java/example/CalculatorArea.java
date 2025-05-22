package example;

public class CalculatorArea {

    // Calcula el área de un rectángulo
    public double calculateRectangleArea(String baseStr, String heightStr) throws IllegalArgumentException {
        double base = parseDouble(baseStr);
        double height = parseDouble(heightStr);

        // Validación de valores negativos o cero
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("La base y la altura deben ser valores positivos");
        }

        return base * height;
    }

    // Convierte String a double con validaciones
    private double parseDouble(String value) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor no puede estar vacío");
        }

        // Reemplaza comas por puntos para manejar formatos numéricos diferentes
        String normalizedValue = value.trim().replace(',', '.');

        try {
            return Double.parseDouble(normalizedValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + value + "' no es un número válido");
        }
    }
}