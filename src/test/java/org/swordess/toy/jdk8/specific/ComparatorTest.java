package org.swordess.toy.jdk8.specific;

import org.junit.Test;
import org.swordess.toy.jdk8.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    @Test
    public void testWithLambdaExpression() {
        List<Card> cards = new ArrayList<>();
        Collections.sort(cards, new SortByRankThenSuit());

        // equivalent but use lambda expression
        Collections.sort(cards, (firstCard, secondCard) -> {
            int compVal = firstCard.getRank().value() - secondCard.getRank().value();
            if (compVal != 0) {
                return compVal;
            }
            return firstCard.getSuit().value() - secondCard.getSuit().value();
        });
    }

    @Test
    public void testWithEnhancedMethods() {
        List<Card> cards = new ArrayList<>();
        // to express usage of Comparator, considering simplified case regardless of suit first
        Collections.sort(cards, (c1, c2) -> c1.getRank().value() - c2.getRank().value());

        // equivalent but use enhanced Comparator
        Collections.sort(cards, Comparator.<Card, Card.Rank>comparing((c) -> c.getRank()));
        // or use more simpler version
        Collections.sort(cards, Comparator.comparing(Card::getRank));

        // then the equivalent of testWithLambdaExpression can be re-implemented as
        Collections.sort(cards, Comparator
                .comparing(Card::getRank)
                .thenComparing(Comparator.comparing(Card::getSuit)));
    }

    public class SortByRankThenSuit implements Comparator<Card> {
        public int compare(Card firstCard, Card secondCard) {
            int compVal = firstCard.getRank().value() - secondCard.getRank().value();
            if (compVal != 0) {
                return compVal;
            }
            return firstCard.getSuit().value() - secondCard.getSuit().value();
        }
    }

}
