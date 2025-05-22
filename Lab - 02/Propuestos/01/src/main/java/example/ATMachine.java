package example;

public class ATMachine {
    private double balance;
    private static final double DEFAULT_INITIAL_BALANCE = 1000.0;

    // Constructor que inicializa el ATM con un saldo predeterminado
    public ATMachine() {
        this.balance = DEFAULT_INITIAL_BALANCE;
    }

    // Constructor que permite establecer un saldo inicial personalizado
    public ATMachine(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.balance = initialBalance;
    }

    // Procesa las operaciones del cajero automático
    public OperationResult processOperation(String operationStr, String amountStr) throws IllegalArgumentException {
        int operation = parseInteger(operationStr);

        switch (operation) {
            case 1: // Consultar Saldo
                return new OperationResult(OperationType.BALANCE_CHECK, balance, balance);

            case 2: // Depositar Dinero
                return deposit(amountStr);

            case 3: // Retirar Dinero
                return withdraw(amountStr);

            case 4: // Salir
                return new OperationResult(OperationType.EXIT, 0, balance);

            default:
                throw new IllegalArgumentException("Operación inválida. Por favor seleccione una opción válida (1-4)");
        }
    }

    // Muestra el saldo actual
    public double getBalance() {
        return balance;
    }

    // Realiza un depósito en la cuenta
    public OperationResult deposit(String amountStr) throws IllegalArgumentException {
        double amount = parseAmount(amountStr);

        // Validar que el monto sea positivo
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser un valor positivo");
        }

        // Actualizar el saldo
        balance += amount;

        return new OperationResult(OperationType.DEPOSIT, amount, balance);
    }

    // Realiza un retiro de la cuenta
    public OperationResult withdraw(String amountStr) throws IllegalArgumentException {
        double amount = parseAmount(amountStr);

        // Validar que el monto sea positivo
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser un valor positivo");
        }

        // Validar que haya suficiente saldo
        if (amount > balance) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro");
        }

        // Actualizar el saldo
        balance -= amount;

        return new OperationResult(OperationType.WITHDRAW, amount, balance);
    }

    // Convierte String a entero con validaciones
    private int parseInteger(String value) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El valor de operación no puede estar vacío");
        }

        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + value + "' no es una opción válida");
        }
    }

    // Convierte String a monto (double) con validaciones
    private double parseAmount(String value) throws IllegalArgumentException {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El monto no puede estar vacío");
        }

        // Reemplaza comas por puntos para manejar formatos numéricos diferentes
        String normalizedValue = value.trim().replace(',', '.');

        try {
            return Double.parseDouble(normalizedValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + value + "' no es un monto válido");
        }
    }

    // Enum para tipos de operación
    public enum OperationType {
        BALANCE_CHECK, DEPOSIT, WITHDRAW, EXIT
    }

    // Clase para encapsular resultados de operaciones
    public static class OperationResult {
        private final OperationType type;
        private final double amount;
        private final double newBalance;

        public OperationResult(OperationType type, double amount, double newBalance) {
            this.type = type;
            this.amount = amount;
            this.newBalance = newBalance;
        }

        public OperationType getType() { return type; }
        public double getAmount() { return amount; }
        public double getNewBalance() { return newBalance; }
    }
}