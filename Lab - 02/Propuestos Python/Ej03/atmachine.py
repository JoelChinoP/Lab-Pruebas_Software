class ATMachine:
    DEFAULT_INITIAL_BALANCE = 1000.0

    def __init__(self, initial_balance=None):
        if initial_balance is None:
            self.balance = ATMachine.DEFAULT_INITIAL_BALANCE
        else:
            if initial_balance < 0:
                raise ValueError("El saldo inicial no puede ser negativo")
            self.balance = initial_balance

    def process_operation(self, operation_str, amount_str=None):
        try:
            operation = self._parse_integer(operation_str)

            if operation == 1:
                return self.check_balance()
            elif operation == 2:
                return self.deposit(amount_str)
            elif operation == 3:
                return self.withdraw(amount_str)
            elif operation == 4:
                return "¡Gracias por utilizar nuestro cajero automático!"
            else:
                return "Error: Operación inválida. Por favor seleccione una opción válida (1-4)."
        except ValueError as e:
            return f"Error: {str(e)}"
        except Exception as e:
            return f"Error inesperado: {str(e)}"

    def check_balance(self):
        return f"Su saldo actual es: S/.{self.balance:.2f}"

    def deposit(self, amount_str):
        try:
            amount = self._parse_amount(amount_str)
            if amount <= 0:
                return "Error: El monto a depositar debe ser un valor positivo."
            self.balance += amount
            return f"Depósito exitoso de S/.{amount:.2f}\n{self.check_balance()}"
        except ValueError as e:
            return f"Error: {str(e)}"

    def withdraw(self, amount_str):
        try:
            amount = self._parse_amount(amount_str)
            if amount <= 0:
                return "Error: El monto a retirar debe ser un valor positivo."
            if amount > self.balance:
                return (f"Error: Saldo insuficiente. Su saldo actual es S/.{self.balance:.2f} "
                        f"y está intentando retirar S/.{amount:.2f}")
            self.balance -= amount
            return f"Retiro exitoso de S/.{amount:.2f}\n{self.check_balance()}"
        except ValueError as e:
            return f"Error: {str(e)}"

    def _parse_integer(self, value):
        if value is None or value.strip() == "":
            raise ValueError("El valor de operación no puede estar vacío")
        try:
            return int(value.strip())
        except ValueError:
            raise ValueError(f"'{value}' no es una opción válida")

    def _parse_amount(self, value):
        if value is None or value.strip() == "":
            raise ValueError("El monto no puede estar vacío")
        normalized_value = value.strip().replace(",", ".")
        try:
            return float(normalized_value)
        except ValueError:
            raise ValueError(f"'{value}' no es un monto válido")

    def get_balance(self):
        return self.balance