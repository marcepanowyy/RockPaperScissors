package org.simulation.logic.utils;

import org.simulation.logic.element.Element;

import java.util.Objects;

public class UniquePair {
    private final Element firstBattleElement;
    private final Element secondBattleElement;

    public UniquePair(Element firstBattleElement, Element secondBattleElement) {
        if (firstBattleElement.hashCode() < secondBattleElement.hashCode()) {
            this.firstBattleElement = firstBattleElement;
            this.secondBattleElement = secondBattleElement;
        } else {
            this.firstBattleElement = secondBattleElement;
            this.secondBattleElement = firstBattleElement;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UniquePair pair = (UniquePair) o;

        return (Objects.equals(firstBattleElement, pair.firstBattleElement) &&
                Objects.equals(secondBattleElement, pair.secondBattleElement)) ||
                (Objects.equals(firstBattleElement, pair.secondBattleElement) &&
                        Objects.equals(secondBattleElement, pair.firstBattleElement));
    }

    @Override
    public int hashCode() {
        int result = firstBattleElement != null ? firstBattleElement.hashCode() : 0;
        result = 31 * result + (secondBattleElement != null ? secondBattleElement.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + firstBattleElement.getSymbol() + ", " + secondBattleElement.getSymbol() + ")";
    }

    public Element getFirstBattleElement() {
        return firstBattleElement;
    }

    public Element getSecondBattleElement() {
        return secondBattleElement;
    }
}
