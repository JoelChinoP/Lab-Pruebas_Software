import unittest
from atmachine import ATMachine

class TestATMachine(unittest.TestCase):
    DELTA = 0.001

    def setUp(self):
        self.atm = ATMachine()  # saldo predeterminado 1000

    def test_initial_balance(self):
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)
        self.assertIn("Su saldo actual es: S/.1000.00", self.atm.check_balance())

    def test_initial_balance_with_custom_amount(self):
        custom_atm = ATMachine(2500.0)
        self.assertAlmostEqual(2500.0, custom_atm.get_balance(), delta=self.DELTA)
        self.assertIn("Su saldo actual es: S/.2500.00", custom_atm.check_balance())

    def test_initial_balance_with_negative_amount(self):
        with self.assertRaises(ValueError):
            ATMachine(-1000.0)

    def test_check_balance_operation(self):
        result = self.atm.process_operation("1", None)
        self.assertIn("Su saldo actual es: S/.1000.00", result)

    def test_deposit_operation(self):
        result = self.atm.process_operation("2", "500")
        self.assertIn("Depósito exitoso de S/.500.00", result)
        self.assertAlmostEqual(1500.0, self.atm.get_balance(), delta=self.DELTA)

    def test_deposit_with_comma_decimal(self):
        result = self.atm.process_operation("2", "250,50")
        self.assertIn("Depósito exitoso de S/.250.50", result)
        self.assertAlmostEqual(1250.50, self.atm.get_balance(), delta=self.DELTA)

    def test_deposit_with_zero_amount(self):
        result = self.atm.process_operation("2", "0")
        self.assertIn("Error: El monto a depositar debe ser un valor positivo", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_deposit_with_negative_amount(self):
        result = self.atm.process_operation("2", "-200")
        self.assertIn("Error: El monto a depositar debe ser un valor positivo", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_deposit_with_invalid_amount(self):
        result = self.atm.process_operation("2", "abc")
        self.assertIn("Error: 'abc' no es un monto válido", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_deposit_with_empty_amount(self):
        result = self.atm.process_operation("2", "")
        self.assertIn("Error: El monto no puede estar vacío", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_deposit_with_null_amount(self):
        result = self.atm.process_operation("2", None)
        self.assertIn("Error: El monto no puede estar vacío", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_withdraw_operation(self):
        result = self.atm.process_operation("3", "300")
        self.assertIn("Retiro exitoso de S/.300.00", result)
        self.assertAlmostEqual(700.0, self.atm.get_balance(), delta=self.DELTA)

    def test_withdraw_with_comma_decimal(self):
        result = self.atm.process_operation("3", "300,50")
        self.assertIn("Retiro exitoso de S/.300.50", result)
        self.assertAlmostEqual(699.50, self.atm.get_balance(), delta=self.DELTA)

    def test_withdraw_exceeding_balance(self):
        result = self.atm.process_operation("3", "1500")
        self.assertIn("Error: Saldo insuficiente", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_withdraw_entire_balance(self):
        result = self.atm.process_operation("3", "1000")
        self.assertIn("Retiro exitoso de S/.1000.00", result)
        self.assertAlmostEqual(0.0, self.atm.get_balance(), delta=self.DELTA)

    def test_withdraw_with_zero_amount(self):
        result = self.atm.process_operation("3", "0")
        self.assertIn("Error: El monto a retirar debe ser un valor positivo", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_withdraw_with_negative_amount(self):
        result = self.atm.process_operation("3", "-200")
        self.assertIn("Error: El monto a retirar debe ser un valor positivo", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_withdraw_with_invalid_amount(self):
        result = self.atm.process_operation("3", "abc")
        self.assertIn("Error: 'abc' no es un monto válido", result)
        self.assertAlmostEqual(1000.0, self.atm.get_balance(), delta=self.DELTA)

    def test_exit_operation(self):
        result = self.atm.process_operation("4", None)
        self.assertIn("¡Gracias por utilizar nuestro cajero automático!", result)

    def test_invalid_operation(self):
        result = self.atm.process_operation("5", None)
        self.assertIn("Error: Operación inválida", result)

    def test_non_numeric_operation(self):
        result = self.atm.process_operation("abc", None)
        self.assertIn("Error: 'abc' no es una opción válida", result)

    def test_empty_operation(self):
        result = self.atm.process_operation("", None)
        self.assertIn("Error: El valor de operación no puede estar vacío", result)

    def test_null_operation(self):
        result = self.atm.process_operation(None, None)
        self.assertIn("Error: El valor de operación no puede estar vacío", result)

    def test_multiple_operations(self):
        self.atm.process_operation("2", "500")  # Depósito 500
        self.atm.process_operation("3", "200")  # Retiro 200
        self.atm.process_operation("2", "300")  # Depósito 300
        self.assertAlmostEqual(1600.0, self.atm.get_balance(), delta=self.DELTA)
        result = self.atm.process_operation("1", None)  # Consultar saldo
        self.assertIn("Su saldo actual es: S/.1600.00", result)


if __name__ == "__main__":
    unittest.main()