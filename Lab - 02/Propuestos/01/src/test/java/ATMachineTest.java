import static org.junit.Assert.*;

import example.ATMachine;
import org.junit.Before;
import org.junit.Test;

public class ATMachineTest {
    private ATMachine atm;
    private static final double DELTA = 0.001; // Delta para comparación de valores dobles

    @Before
    public void setUp() {
        atm = new ATMachine(); // Inicializar con saldo predeterminado de 1000
    }

    @Test
    public void testInitialBalance() {
        assertEquals(1000.0, atm.getBalance(), DELTA);
        assertTrue(atm.checkBalance().contains("Su saldo actual es: S/.1000.00"));
    }

    @Test
    public void testInitialBalanceWithCustomAmount() {
        ATMachine customAtm = new ATMachine(2500.0);
        assertEquals(2500.0, customAtm.getBalance(), DELTA);
        assertTrue(customAtm.checkBalance().contains("Su saldo actual es: S/.2500.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitialBalanceWithNegativeAmount() {
        new ATMachine(-1000.0);
    }

    @Test
    public void testCheckBalanceOperation() {
        String result = atm.processOperation("1", null);
        assertTrue(result.contains("Su saldo actual es: S/.1000.00"));
    }

    @Test
    public void testDepositOperation() {
        String result = atm.processOperation("2", "500");
        assertTrue(result.contains("Depósito exitoso de S/.500.00"));
        assertEquals(1500.0, atm.getBalance(), DELTA);
    }

    @Test
    public void testDepositWithCommaDecimal() {
        String result = atm.processOperation("2", "250,50");
        assertTrue(result.contains("Depósito exitoso de S/.250.50"));
        assertEquals(1250.50, atm.getBalance(), DELTA);
    }

    @Test
    public void testDepositWithZeroAmount() {
        String result = atm.processOperation("2", "0");
        assertTrue(result.contains("Error: El monto a depositar debe ser un valor positivo"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testDepositWithNegativeAmount() {
        String result = atm.processOperation("2", "-200");
        assertTrue(result.contains("Error: El monto a depositar debe ser un valor positivo"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testDepositWithInvalidAmount() {
        String result = atm.processOperation("2", "abc");
        assertTrue(result.contains("Error: 'abc' no es un monto válido"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testDepositWithEmptyAmount() {
        String result = atm.processOperation("2", "");
        assertTrue(result.contains("Error: El monto no puede estar vacío"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testDepositWithNullAmount() {
        String result = atm.processOperation("2", null);
        assertTrue(result.contains("Error: El monto no puede estar vacío"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testWithdrawOperation() {
        String result = atm.processOperation("3", "300");
        assertTrue(result.contains("Retiro exitoso de S/.300.00"));
        assertEquals(700.0, atm.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawWithCommaDecimal() {
        String result = atm.processOperation("3", "300,50");
        assertTrue(result.contains("Retiro exitoso de S/.300.50"));
        assertEquals(699.50, atm.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawExceedingBalance() {
        String result = atm.processOperation("3", "1500");
        assertTrue(result.contains("Error: Saldo insuficiente"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testWithdrawEntireBalance() {
        String result = atm.processOperation("3", "1000");
        assertTrue(result.contains("Retiro exitoso de S/.1000.00"));
        assertEquals(0.0, atm.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawWithZeroAmount() {
        String result = atm.processOperation("3", "0");
        assertTrue(result.contains("Error: El monto a retirar debe ser un valor positivo"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testWithdrawWithNegativeAmount() {
        String result = atm.processOperation("3", "-200");
        assertTrue(result.contains("Error: El monto a retirar debe ser un valor positivo"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testWithdrawWithInvalidAmount() {
        String result = atm.processOperation("3", "abc");
        assertTrue(result.contains("Error: 'abc' no es un monto válido"));
        assertEquals(1000.0, atm.getBalance(), DELTA); // Saldo no cambia
    }

    @Test
    public void testExitOperation() {
        String result = atm.processOperation("4", null);
        assertTrue(result.contains("¡Gracias por utilizar nuestro cajero automático!"));
    }

    @Test
    public void testInvalidOperation() {
        String result = atm.processOperation("5", null);
        assertTrue(result.contains("Error: Operación inválida"));
    }

    @Test
    public void testNonNumericOperation() {
        String result = atm.processOperation("abc", null);
        assertTrue(result.contains("Error: 'abc' no es una opción válida"));
    }

    @Test
    public void testEmptyOperation() {
        String result = atm.processOperation("", null);
        assertTrue(result.contains("Error: El valor de operación no puede estar vacío"));
    }

    @Test
    public void testNullOperation() {
        String result = atm.processOperation(null, null);
        assertTrue(result.contains("Error: El valor de operación no puede estar vacío"));
    }

    @Test
    public void testMultipleOperations() {
        // Realizar una secuencia de operaciones
        atm.processOperation("2", "500"); // Depósito de 500
        atm.processOperation("3", "200"); // Retiro de 200
        atm.processOperation("2", "300"); // Depósito de 300

        assertEquals(1600.0, atm.getBalance(), DELTA);

        String result = atm.processOperation("1", null); // Consultar saldo
        assertTrue(result.contains("Su saldo actual es: S/.1600.00"));
    }
}