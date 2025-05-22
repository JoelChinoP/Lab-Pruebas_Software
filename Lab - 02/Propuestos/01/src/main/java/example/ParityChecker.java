package example;

public class ParityChecker {

    // Procesa una lista de números para determinar si son pares o impares
    public boolean[] checkParity(String countStr, String[] numbers) throws IllegalArgumentException {
        int count = parsePositiveInteger(countStr);

        // Validar que se hayan proporcionado números
        if (numbers == null) {
            throw new IllegalArgumentException("No se proporcionaron números para verificar");
        }

        if (numbers.length != count) {
            throw new IllegalArgumentException("La cantidad de números proporcionados (" + numbers.length +
                    ") no coincide con la cantidad especificada (" + count + ")");
        }

        boolean[] results = new boolean[count];

        // Procesar cada número de la lista
        for (int i = 0; i < count; i++) {
            int number = parseInteger(numbers[i]);
            results[i] = (number % 2 == 0); // true = par, false = impar
        }

        return results;
    }

    // Convierte String a entero positivo con validaciones
    private int parsePositiveInteger(String value) throws IllegalArgumentException {
        int number = parseInteger(value);

        if (number <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser un número entero positivo");
        }

        return number;
    }

    // Convierte String a entero con validaciones
    private int parseInteger(String value) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor no puede estar vacío");
        }

        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + value + "' no es un número entero válido");
        }
    }
}