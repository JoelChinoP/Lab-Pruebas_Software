import unittest
from calculator_area import CalculatorArea

class TestCalculatorArea(unittest.TestCase):
    def setUp(self):
        self.calculator = CalculatorArea()

    def test_with_integers(self):
        "Prueba con números enteros"
        result = self.calculator.calculate_rectangle_area("5", "7")
        self.assertIn("base: 5", result)
        self.assertIn("altura: 7", result)
        self.assertIn("Área calculada: 35", result)

    def test_with_decimals(self):
        "Prueba con números decimales"
        result = self.calculator.calculate_rectangle_area("3.5", "2.5")
        self.assertIn("base: 3.5", result)
        self.assertIn("altura: 2.5", result)
        self.assertIn("Área calculada: 8.75", result)

    def test_with_comma_decimals(self):
        "Prueba con números decimales con coma"
        result = self.calculator.calculate_rectangle_area("2,5", "4,0")
        self.assertIn("base: 2.5", result)
        self.assertTrue("altura: 4.0" in result or "altura: 4" in result)
        self.assertIn("Área calculada: 10", result)

    def test_with_zero_value(self):
        "Prueba con valor cero"
        result = self.calculator.calculate_rectangle_area("0", "5")
        self.assertIn("Error: La base y la altura deben ser valores positivos", result)

    def test_with_negative_value(self):
        "Prueba con valor negativo"
        result = self.calculator.calculate_rectangle_area("-3", "2")
        self.assertIn("Error: La base y la altura deben ser valores positivos", result)

    def test_with_non_numeric(self):
        "Prueba con valor no numérico"
        result = self.calculator.calculate_rectangle_area("abc", "5")
        self.assertIn("Error: Ingrese valores numéricos válidos", result)

    def test_with_empty_input(self):
        "Prueba con entrada vacía"
        result = self.calculator.calculate_rectangle_area("", "5")
        self.assertIn("Error: Ingrese valores numéricos válidos", result)

    def test_with_null_input(self):
        "Prueba con entrada nula"
        result = self.calculator.calculate_rectangle_area(None, "5")
        self.assertIn("Error: Ingrese valores numéricos válidos", result)

    def test_with_large_numbers(self):
        "Prueba con números grandes"
        result = self.calculator.calculate_rectangle_area("1000000", "2000000")
        self.assertIn("Área calculada: 2000000000000", result)

    def test_with_small_decimals(self):
        "Prueba con números decimales pequeños"
        result = self.calculator.calculate_rectangle_area("0.1", "0.1")
        self.assertIn("Área calculada: 0.01", result)

if __name__ == '__main__':
    unittest.main()
