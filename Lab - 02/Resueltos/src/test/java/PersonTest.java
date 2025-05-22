import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class PersonTest {

    @Test
    public void whenAgeIs18_thenIsAdult() {
        Person p = new Person("Alicia", 18);
        // matcher "is(true)" viene de hamcrest-core
        assertThat("Debería ser adulto a los 18", p.isAdult(), is(true));
    }

    @Test
    public void whenAgeLessThan18_thenNotAdult() {
        Person p = new Person("Roberto", 12);
        assertThat("No debería ser adulto si tiene menos de 18", p.isAdult(), is(false));
    }

    @Test
    public void nameShouldBeStoredCorrectlyAndNotNull() {
        Person p = new Person("Eveling", 30);
        // notNullValue() y equalTo() de hamcrest-core
        assertThat(p.getName(), notNullValue());
        assertThat(p.getName(), equalTo("Eveling"));
    }

    @Test
    public void personInstanceShouldBeOfTypePerson() {
        Person p = new Person("Carlos", 25);
        // instanceOf(...) de hamcrest-core
        assertThat(p, instanceOf(Person.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldRejectEmptyName() {
        new Person("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldRejectNegativeAge() {
        new Person("David", -5);
    }
}
