public class ATMachine {
    private double balance;
    private static final double DEFAULT_INITIAL_BALANCE = 1000.0;
    
    /**
     * Constructor que inicializa el ATM con un saldo predeterminado
     */
    public ATMachine() {
        this.balance = DEFAULT_INITIAL_BALANCE;
    }
    
    /**
     * Constructor que permite establecer un saldo inicial personalizado
     * @param initialBalance El saldo inicial
     * @throws IllegalArgumentException si el saldo inicial es negativo
     */
    public ATMachine(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.balance = initialBalance;
    }
    
    /**
     * Procesa las operaciones del cajero automático
     * @param operationStr El código de operación (1-4)
     * @param amountStr El monto para depósito o retiro (opcional)
     * @return Un mensaje con el resultado de la operación
     */
    public String processOperation(String operationStr, String amountStr) {
        try {
            // Validar que la operación sea un número entero
            int operation = parseInteger(operationStr);
            
            switch (operation) {
                case 1: // Consultar Saldo
                    return checkBalance();
                    
                case 2: // Depositar Dinero
                    return deposit(amountStr);
                    
                case 3: // Retirar Dinero
                    return withdraw(amountStr);
                    
                case 4: // Salir
                    return "¡Gracias por utilizar nuestro cajero automático!";
                    
                default:
                    return "Error: Operación inválida. Por favor seleccione una opción válida (1-4).";
            }
        } catch (NumberFormatException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
    
    /**
     * Muestra el saldo actual
     * @return Un mensaje con el saldo actual
     */
    public String checkBalance() {
        return String.format("Su saldo actual es: S/.%.2f", balance);
    }
    
    /**
     * Realiza un depósito en la cuenta
     * @param amountStr El monto a depositar como cadena
     * @return Un mensaje con el resultado del depósito
     * @throws NumberFormatException si el monto no es un número válido
     */
    public String deposit(String amountStr) {
        try {
            double amount = parseAmount(amountStr);
            
            // Validar que el monto sea positivo
            if (amount <= 0) {
                return "Error: El monto a depositar debe ser un valor positivo.";
            }
            
            // Actualizar el saldo
            balance += amount;
            
            return String.format("Depósito exitoso de S/.%.2f\n%s", amount, checkBalance());
        } catch (NumberFormatException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    /**
     * Realiza un retiro de la cuenta
     * @param amountStr El monto a retirar como cadena
     * @return Un mensaje con el resultado del retiro
     * @throws NumberFormatException si el monto no es un número válido
     */
    public String withdraw(String amountStr) {
        try {
            double amount = parseAmount(amountStr);
            
            // Validar que el monto sea positivo
            if (amount <= 0) {
                return "Error: El monto a retirar debe ser un valor positivo.";
            }
            
            // Validar que haya suficiente saldo
            if (amount > balance) {
                return String.format(
                    "Error: Saldo insuficiente. Su saldo actual es S/.%.2f y está intentando retirar S/.%.2f", 
                    balance, amount);
            }
            
            // Actualizar el saldo
            balance -= amount;
            
            return String.format("Retiro exitoso de S/.%.2f\n%s", amount, checkBalance());
        } catch (NumberFormatException e) {
            return "Error: " + e.getMessage();
        }
    }
    
    /**
     * Intenta parsear un String a un entero
     * @param value El valor a convertir
     * @return El valor como entero
     * @throws NumberFormatException si el valor no es un entero válido
     */
    private int parseInteger(String value) throws NumberFormatException {
        if (value == null || value.trim().isEmpty()) {
            throw new NumberFormatException("El valor de operación no puede estar vacío");
        }
        
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("'" + value + "' no es una opción válida");
        }
    }
    
    /**
     * Intenta parsear un String a un monto (double)
     * @param value El valor a convertir
     * @return El valor como double
     * @throws NumberFormatException si el valor no es un número válido
     */
    private double parseAmount(String value) throws NumberFormatException {
        if (value == null || value.trim().isEmpty()) {
            throw new NumberFormatException("El monto no puede estar vacío");
        }
        
        // Reemplaza comas por puntos para manejar formatos numéricos diferentes
        String normalizedValue = value.trim().replace(',', '.');
        
        try {
            return Double.parseDouble(normalizedValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("'" + value + "' no es un monto válido");
        }
    }
    
    /**
     * Obtiene el saldo actual (para pruebas)
     * @return El saldo actual
     */
    public double getBalance() {
        return balance;
    }
    
}