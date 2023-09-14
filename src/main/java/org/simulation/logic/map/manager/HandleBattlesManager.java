package org.simulation.logic.map.manager;

import org.simulation.logic.element.Element;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.utils.UniquePair;

public class HandleBattlesManager {

    private final WorldMap worldMap;
    private final ElementFactory elementFactory;

    public HandleBattlesManager(WorldMap worldMap) {

        this.worldMap = worldMap;
        this.elementFactory = new ElementFactory();

    }

    public void handleBattles() {

        for (UniquePair battleElements : worldMap.getUniqueBattlePairs()) {

            handleBattle(battleElements.getFirstBattleElement(),battleElements.getSecondBattleElement());

        }

        worldMap.getUniqueBattlePairs().clear();
    }

    private void handleBattle(Element element, Element opponent){

        boolean battleWon = element.battle(opponent);
        Element newElement;

        if (battleWon) {

            newElement = elementFactory.createElement(element.getSymbol(), opponent.getPosition());
            element.setOpponent(null);
            worldMap.getMapElementsManager().removeElement(opponent);
            worldMap.getRemovedRoundElements().put(opponent, opponent.getPosition());

        }

        else  {

            newElement = elementFactory.createElement(opponent.getSymbol(), element.getPosition());
            opponent.setOpponent(null);
            worldMap.getMapElementsManager().removeElement(element);
            worldMap.getRemovedRoundElements().put(element, element.getPosition());

        }

        worldMap.getMapElementsManager().addElement(newElement);

    }

}
