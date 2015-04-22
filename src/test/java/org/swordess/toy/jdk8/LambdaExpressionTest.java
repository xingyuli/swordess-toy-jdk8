package org.swordess.toy.jdk8;

import org.junit.Test;
import org.swordess.toy.jdk8.model.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * See: http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class LambdaExpressionTest {

    @Test
    public void test() {
        List<Person> persons = Arrays.asList(
                new Person("Jack", 35, "jack@example.com"),
                new Person("Rose", 33, "rose@example.com"),
                new Person("Mary", 28, "mary@example.com")
        );

        print(persons, p -> p.getAge() > 30);
        applyIf(persons, p -> p.getAge() > 30, p -> System.out.println(p.getEmailAddress()));

        processElements(
                persons,
                p -> p.getAge() > 30,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );

        persons.stream()
                .filter(p -> p.getAge() > 30)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));
    }

    private static <T> void print(Collection<T> cc, Predicate<T> predicate) {
        for (T c : cc) {
            if (predicate.test(c)) {
                System.out.println(c);
            }
        }
    }

    private static <T> void applyIf(Collection<T> cc, Predicate<T> predicate, Consumer<T> block) {
        for (T c : cc) {
            if (predicate.test(c)) {
                block.accept(c);
            }
        }
    }

    private static <X, Y> void processElements(Iterable<X> source, Predicate<X> predicate, Function<X, Y> mapper, Consumer<Y> block) {
        for (X e : source) {
            if (predicate.test(e)) {
                block.accept(mapper.apply(e));
            }
        }
    }

}
