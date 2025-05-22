import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.closeTo;
import example.ATMachine;
import example.ATMachine.OperationResult;
import example.ATMachine.OperationType;
import org.junit.Before;
import org.junit.Test;

public class ATMachineTest {
    private ATMachine atm;
    private static final double DELTA = 0.001;

    @Before
    public void setUp() {
        atm = new ATMachine(); // Inicializar con saldo predeterminado de 1000
    }

    @Test
    public void whenInitializedWithDefaultBalance_thenShouldHave1000() {
        assertThat("El saldo inicial debe ser 1000", atm.getBalance(), is(closeTo(1000.0, DELTA)));
    }

    @Test
    public void whenInitializedWithCustomAmount_thenShouldHaveCustomBalance() {
        ATMachine customAtm = new ATMachine(2500.0);
        assertThat("El saldo debe ser el valor personalizado",
                customAtm.getBalance(), is(closeTo(2500.0, DELTA)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInitializedWithNegativeAmount_thenThrowIllegalArgumentException() {
        new ATMachine(-1000.0);
    }

    @Test
    public void whenCheckingBalance_thenReturnBalanceCheckResult() {
        OperationResult result = atm.processOperation("1", null);
        assertThat("Debe ser operación de consulta de saldo",
                result.getType(), is(OperationType.BALANCE_CHECK));
        assertThat("Debe retornar el saldo actual",
                result.getNewBalance(), is(closeTo(1000.0, DELTA)));
    }

    @Test
    public void whenDepositing_thenReturnDepositResult() {
        OperationResult result = atm.processOperation("2", "500");
        assertThat("Debe ser operación de depósito", result.getType(), is(OperationType.DEPOSIT));
        assertThat("El monto debe ser 500", result.getAmount(), is(closeTo(500.0, DELTA)));
        assertThat("El nuevo saldo debe ser 1500", result.getNewBalance(), is(closeTo(1500.0, DELTA)));
        assertThat("El saldo del ATM debe actualizarse", atm.getBalance(), is(closeTo(1500.0, DELTA)));
    }

    @Test
    public void whenDepositingWithCommaDecimal_thenReturnCorrectResult() {
        OperationResult result = atm.processOperation("2", "250,50");
        assertThat("El monto debe ser 250.50", result.getAmount(), is(closeTo(250.50, DELTA)));
        assertThat("El nuevo saldo debe ser 1250.50", result.getNewBalance(), is(closeTo(1250.50, DELTA)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDepositingZeroAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("2", "0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDepositingNegativeAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("2", "-200");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDepositingInvalidAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("2", "abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDepositingEmptyAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("2", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDepositingNullAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("2", null);
    }

    @Test
    public void whenWithdrawing_thenReturnWithdrawResult() {
        OperationResult result = atm.processOperation("3", "300");
        assertThat("Debe ser operación de retiro", result.getType(), is(OperationType.WITHDRAW));
        assertThat("El monto debe ser 300", result.getAmount(), is(closeTo(300.0, DELTA)));
        assertThat("El nuevo saldo debe ser 700", result.getNewBalance(), is(closeTo(700.0, DELTA)));
        assertThat("El saldo del ATM debe actualizarse", atm.getBalance(), is(closeTo(700.0, DELTA)));
    }

    @Test
    public void whenWithdrawingWithCommaDecimal_thenReturnCorrectResult() {
        OperationResult result = atm.processOperation("3", "300,50");
        assertThat("El monto debe ser 300.50", result.getAmount(), is(closeTo(300.50, DELTA)));
        assertThat("El nuevo saldo debe ser 699.50", result.getNewBalance(), is(closeTo(699.50, DELTA)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithdrawingExceedingBalance_thenThrowIllegalArgumentException() {
        atm.processOperation("3", "1500");
    }

    @Test
    public void whenWithdrawingEntireBalance_thenReturnCorrectResult() {
        OperationResult result = atm.processOperation("3", "1000");
        assertThat("El nuevo saldo debe ser 0", result.getNewBalance(), is(closeTo(0.0, DELTA)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithdrawingZeroAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("3", "0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithdrawingNegativeAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("3", "-200");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithdrawingInvalidAmount_thenThrowIllegalArgumentException() {
        atm.processOperation("3", "abc");
    }

    @Test
    public void whenExiting_thenReturnExitResult() {
        OperationResult result = atm.processOperation("4", null);
        assertThat("Debe ser operación de salida", result.getType(), is(OperationType.EXIT));
        assertThat("El saldo no debe cambiar", result.getNewBalance(), is(closeTo(1000.0, DELTA)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidOperation_thenThrowIllegalArgumentException() {
        atm.processOperation("5", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNonNumericOperation_thenThrowIllegalArgumentException() {
        atm.processOperation("abc", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyOperation_thenThrowIllegalArgumentException() {
        atm.processOperation("", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullOperation_thenThrowIllegalArgumentException() {
        atm.processOperation(null, null);
    }

    @Test
    public void whenPerformingMultipleOperations_thenShouldMaintainCorrectBalance() {
        // Realizar una secuencia de operaciones
        atm.processOperation("2", "500"); // Depósito de 500
        atm.processOperation("3", "200"); // Retiro de 200
        atm.processOperation("2", "300"); // Depósito de 300

        assertThat("El saldo final debe ser 1600", atm.getBalance(), is(closeTo(1600.0, DELTA)));

        OperationResult result = atm.processOperation("1", null); // Consultar saldo
        assertThat("La consulta debe mostrar el saldo correcto",
                result.getNewBalance(), is(closeTo(1600.0, DELTA)));
    }

    @Test
    public void atmInstanceShouldBeOfTypeATMachine() {
        assertThat("La instancia debe ser de tipo ATMachine",
                atm, instanceOf(ATMachine.class));
    }

    @Test
    public void whenPerformingOperations_thenResultsShouldNotBeNull() {
        OperationResult depositResult = atm.processOperation("2", "100");
        OperationResult balanceResult = atm.processOperation("1", null);

        assertThat("El resultado del depósito no debe ser null", depositResult, notNullValue());
        assertThat("El resultado de consulta de saldo no debe ser null", balanceResult, notNullValue());
        assertThat("El tipo de operación no debe ser null", depositResult.getType(), notNullValue());
    }
}