class ParityChecker:
    def check_parity(self, count_str, numbers):
        try:
            count = self.parse_positive_integer(count_str)
            
            if numbers is None:
                return "Error: No se proporcionaron números para verificar."
            
            if len(numbers) != count:
                return (f"Error: La cantidad de números proporcionados ({len(numbers)}) "
                        f"no coincide con la cantidad especificada ({count}).")
            
            result = ["Resultados de la verificación de paridad:\n"]
            
            for num_str in numbers:
                try:
                    number = self.parse_integer(num_str)
                    parity = "par" if number % 2 == 0 else "impar"
                    result.append(f"{number} es {parity}\n")
                except ValueError as e:
                    result.append(f"'{num_str}' no es un número entero válido\n")
            
            return "".join(result)
        except ValueError as e:
            return f"Error: {str(e)}"
        except Exception as e:
            return f"Error inesperado: {str(e)}"
    
    def parse_positive_integer(self, value):
        number = self.parse_integer(value)
        if number <= 0:
            raise ValueError("La cantidad debe ser un número entero positivo")
        return number
    
    def parse_integer(self, value):
        if value is None or value.strip() == "":
            raise ValueError("El valor no puede estar vacío")
        try:
            return int(value.strip())
        except Exception:
            raise ValueError(f"'{value}' no es un número entero válido")
