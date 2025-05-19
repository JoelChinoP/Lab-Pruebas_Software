public class ParityChecker {
    
    /**
     * Procesa una lista de números para determinar si son pares o impares
     * @param countStr La cantidad de números que se ingresarán
     * @param numbers Un array con los números a comprobar
     * @return Un mensaje con los resultados o un mensaje de error
     */
    public String checkParity(String countStr, String[] numbers) {
        try {
            // Validar que la cantidad sea un número entero positivo
            int count = parsePositiveInteger(countStr);
            
            // Validar que se hayan proporcionado la cantidad correcta de números
            if (numbers == null) {
                return "Error: No se proporcionaron números para verificar.";
            }
            
            if (numbers.length != count) {
                return "Error: La cantidad de números proporcionados (" + numbers.length + 
                       ") no coincide con la cantidad especificada (" + count + ").";
            }
            
            StringBuilder result = new StringBuilder("Resultados de la verificación de paridad:\n");
            
            // Procesar cada número de la lista
            for (int i = 0; i < count; i++) {
                try {
                    int number = parseInteger(numbers[i]);
                    String parity = (number % 2 == 0) ? "par" : "impar";
                    result.append(number).append(" es ").append(parity).append("\n");
                } catch (NumberFormatException e) {
                    result.append("'").append(numbers[i]).append("' no es un número entero válido\n");
                }
            }
            
            return result.toString();
        } catch (NumberFormatException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
    
    /**
     * Intenta parsear un String a un entero positivo
     * @param value El valor a convertir
     * @return El valor como entero
     * @throws NumberFormatException si el valor no es un entero positivo válido
     */
    private int parsePositiveInteger(String value) throws NumberFormatException {
        int number = parseInteger(value);
        
        if (number <= 0) {
            throw new NumberFormatException("La cantidad debe ser un número entero positivo");
        }
        
        return number;
    }
    
    /**
     * Intenta parsear un String a un entero
     * @param value El valor a convertir
     * @return El valor como entero
     * @throws NumberFormatException si el valor no es un entero válido
     */
    private int parseInteger(String value) throws NumberFormatException {
        if (value == null || value.trim().isEmpty()) {
            throw new NumberFormatException("El valor no puede estar vacío");
        }
        
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("'" + value + "' no es un número entero válido");
        }
    }

}