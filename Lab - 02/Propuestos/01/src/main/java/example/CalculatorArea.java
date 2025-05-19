package example;

public class CalculatorArea {
    
    /**
     * Calcula el área de un rectángulo y muestra el resultado
     * @param baseStr La base del rectángulo como cadena de texto
     * @param heightStr La altura del rectángulo como cadena de texto
     * @return Un mensaje con los detalles del cálculo o un mensaje de error
     */
    public String calculateRectangleArea(String baseStr, String heightStr) {
        try {
            // Intenta convertir las cadenas a números
            double base = parseDouble(baseStr);
            double height = parseDouble(heightStr);
            
            // Validación de valores negativos o cero
            if (base <= 0 || height <= 0) {
                return "Error: La base y la altura deben ser valores positivos.";
            }
            
            // Cálculo del área
            double area = base * height;
            
            // Formato de salida dependiendo si los números son enteros o decimales
            String baseFormatted = formatNumber(base);
            String heightFormatted = formatNumber(height);
            String areaFormatted = formatNumber(area);
            
            return String.format("Rectángulo con base: %s, altura: %s\nÁrea calculada: %s", 
                    baseFormatted, heightFormatted, areaFormatted);
        } catch (NumberFormatException e) {
            return "Error: Ingrese valores numéricos válidos para la base y la altura.";
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
    
    /**
     * Intenta parsear un String a double, verificando que sea un número válido
     * @param value El valor a convertir
     * @return El valor como double
     * @throws NumberFormatException si el valor no es un número válido
     */
    private double parseDouble(String value) throws NumberFormatException {
        if (value == null || value.trim().isEmpty()) {
            throw new NumberFormatException("El valor no puede estar vacío");
        }
        
        // Reemplaza comas por puntos para manejar formatos numéricos diferentes
        String normalizedValue = value.trim().replace(',', '.');
        
        try {
            return Double.parseDouble(normalizedValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("'" + value + "' no es un número válido");
        }
    }
    
    /**
     * Formatea un número para mostrarlo sin decimales si es entero
     * @param number El número a formatear
     * @return El número formateado como String
     */
    private String formatNumber(double number) {
        if (number == (int) number) {
            return String.valueOf((int) number);
        } else {
            return String.valueOf(number);
        }
    }

}