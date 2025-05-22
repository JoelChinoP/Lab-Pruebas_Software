class CalculatorArea:
    def calculate_rectangle_area(self, base_str, height_str):
        try:
            base = self._parse_double(base_str)
            height = self._parse_double(height_str)

            if base <= 0 or height <= 0:
                return "Error: La base y la altura deben ser valores positivos."

            area = base * height

            base_formatted = self._format_number(base)
            height_formatted = self._format_number(height)
            area_formatted = self._format_number(area)

            return f"Rectángulo con base: {base_formatted}, altura: {height_formatted}\nÁrea calculada: {area_formatted}"

        except ValueError:
            return "Error: Ingrese valores numéricos válidos para la base y la altura."
        except Exception as e:
            return f"Error inesperado: {str(e)}"

    def _parse_double(self, value):
        if value is None or value.strip() == "":
            raise ValueError("El valor no puede estar vacío")

        normalized_value = value.strip().replace(',', '.')
        try:
            return float(normalized_value)
        except ValueError:
            raise ValueError(f"'{value}' no es un número válido")

    def _format_number(self, number):
        if number == int(number):
            return str(int(number))
        else:
            return str(number)
