/**
 * Representa a una persona con nombre y edad.
 * Lanza IllegalArgumentException si el nombre está vacío o la edad es negativa.
 */
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (age < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /** Devuelve true si la edad es ≥ 18 */
    public boolean isAdult() {
        return age >= 18;
    }
}
