import unittest
from parity_checker import ParityChecker

class TestParityChecker(unittest.TestCase):
    def setUp(self):
        self.checker = ParityChecker()

    def test_check_parity_with_positive_numbers(self):
        result = self.checker.check_parity("3", ["1", "2", "3"])
        self.assertIn("1 es impar", result)
        self.assertIn("2 es par", result)
        self.assertIn("3 es impar", result)

    def test_check_parity_with_zero(self):
        result = self.checker.check_parity("1", ["0"])
        self.assertIn("0 es par", result)

    def test_check_parity_with_negative_numbers(self):
        result = self.checker.check_parity("3", ["-1", "-2", "-3"])
        self.assertIn("-1 es impar", result)
        self.assertIn("-2 es par", result)
        self.assertIn("-3 es impar", result)

    def test_check_parity_with_mixed_numbers(self):
        result = self.checker.check_parity("4", ["0", "-5", "10", "21"])
        self.assertIn("0 es par", result)
        self.assertIn("-5 es impar", result)
        self.assertIn("10 es par", result)
        self.assertIn("21 es impar", result)

    def test_check_parity_with_invalid_count(self):
        result = self.checker.check_parity("-1", ["1", "2"])
        self.assertIn("Error: La cantidad debe ser un número entero positivo", result)

    def test_check_parity_with_zero_count(self):
        result = self.checker.check_parity("0", [])
        self.assertIn("Error: La cantidad debe ser un número entero positivo", result)

    def test_check_parity_with_non_numeric_count(self):
        result = self.checker.check_parity("abc", ["1", "2", "3"])
        self.assertIn("Error: 'abc' no es un número entero válido", result)

    def test_check_parity_with_empty_count(self):
        result = self.checker.check_parity("", ["1", "2"])
        self.assertIn("Error: El valor no puede estar vacío", result)

    def test_check_parity_with_null_count(self):
        result = self.checker.check_parity(None, ["1", "2"])
        self.assertIn("Error: El valor no puede estar vacío", result)

    def test_check_parity_with_non_numeric_values(self):
        result = self.checker.check_parity("3", ["1", "abc", "3"])
        self.assertIn("1 es impar", result)
        self.assertIn("'abc' no es un número entero válido", result)
        self.assertIn("3 es impar", result)

    def test_check_parity_with_empty_values(self):
        result = self.checker.check_parity("2", ["1", ""])
        self.assertIn("1 es impar", result)
        self.assertIn("'' no es un número entero válido", result)

    def test_check_parity_with_null_values(self):
        result = self.checker.check_parity("2", ["1", None])
        self.assertIn("1 es impar", result)
        self.assertTrue("'None' no es un número entero válido" in result or
                        "El valor no puede estar vacío" in result)

    def test_check_parity_with_mismatched_count(self):
        result = self.checker.check_parity("5", ["1", "2", "3"])
        self.assertIn("Error: La cantidad de números proporcionados (3) no coincide con la cantidad especificada (5)", result)

    def test_check_parity_with_null_array(self):
        result = self.checker.check_parity("3", None)
        self.assertIn("Error: No se proporcionaron números para verificar", result)

    def test_check_parity_with_large_numbers(self):
        result = self.checker.check_parity("2", ["2147483647", "-2147483648"])
        self.assertIn("2147483647 es impar", result)
        self.assertIn("-2147483648 es par", result)

if __name__ == "__main__":
    unittest.main()
