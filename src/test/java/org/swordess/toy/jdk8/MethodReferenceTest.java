package org.swordess.toy.jdk8;

import org.junit.Test;
import org.swordess.toy.jdk8.model.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Supplier;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * See: http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 */
public class MethodReferenceTest {

    @Test
    public void testWithLambdaExpression() {
        Person[] personsAsArray = newPersonArray();

        Arrays.sort(personsAsArray, (a, b) -> a.getAge() - b.getAge());

        assertEquals("Mary", personsAsArray[0].getName());
        assertEquals("Rose", personsAsArray[1].getName());
        assertEquals("Jack", personsAsArray[2].getName());
    }

    @Test
    public void testReferenceToAStaticMethod() {
        Person[] personsAsArray = newPersonArray();

        // semantically equivalent to:
        // Arrays.sort(personsAsArray, (a, b) -> Person.compareByAge(a, b));
        Arrays.sort(personsAsArray, Person::compareByAge);

        assertEquals("Mary", personsAsArray[0].getName());
        assertEquals("Rose", personsAsArray[1].getName());
        assertEquals("Jack", personsAsArray[2].getName());
    }

    @Test
    public void testReferenceToAnInstanceMethodOfAParticularObject() {
        Person[] personsAsArray = newPersonArray();

        ComparatorProvider comparatorProvider = new ComparatorProvider();

        // semantically equivalent to:
        // Arrays.sort(personsAsArray, (a, b) -> comparatorProvider.compareByName(a, b));
        Arrays.sort(personsAsArray, comparatorProvider::compareByName);
    }

    private static class ComparatorProvider {

        public int compareByName(Person a, Person b) {
            return a.getName().compareTo(b.getName());
        }

    }

    @Test
    public void testReferenceToAnInstanceMethodOfAnArbitraryObjectOfAParticularType() {
        String[] words = { "Hello", "my", "friend" };

        // semantically equivalent to:
        // Arrays.sort(words, (a, b) -> a.compareToIgnoreCase(b));
        Arrays.sort(words, String::compareToIgnoreCase);

        assertEquals("friend", words[0]);
        assertEquals("Hello", words[1]);
        assertEquals("my", words[2]);
    }

    @Test
    public void testReferenceToAConstructor() {
        String[] weekdays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        // semantically equivalent to:
        // arrayIntoCollection(weekdays, () -> new TreeSet<>()).toArray(new String[0]);
        String[] actuals = arrayIntoCollection(weekdays, TreeSet::new).toArray(new String[0]);

        String[] expecteds = { "Friday", "Monday", "Saturday", "Sunday", "Thursday", "Tuesday", "Wednesday" };

        assertArrayEquals(expecteds, actuals);
    }

    private static Collection<String> arrayIntoCollection(String[] arr, Supplier<Collection<String>> supplier) {
        Collection<String> c = supplier.get();
        for (String e : arr) {
            c.add(e);
        }
        return c;
    }

    private static Person[] newPersonArray() {
        return new Person[] {
                new Person("Jack", 35, "jack@example.com"),
                new Person("Rose", 33, "rose@example.com"),
                new Person("Mary", 28, "mary@example.com")
        };
    }

}
