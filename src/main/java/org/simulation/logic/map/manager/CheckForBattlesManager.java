package org.simulation.logic.map.manager;

import org.simulation.logic.element.Element;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.utils.UniquePair;

public class CheckForBattlesManager {

    private final WorldMap worldMap;

    public CheckForBattlesManager(WorldMap worldMap) {

        this.worldMap = worldMap;

    }

    public void checkForBattles() {

        for (Element element : worldMap.getElements()) {

            Element opponent = element.getOpponent();

            if (opponent != null) {

                double distance = element.calculateDistanceToOther(opponent);

                if (distance <= worldMap.getBattleRange() && element == opponent.getOpponent()) {

                    UniquePair battlePair = new UniquePair(element, opponent);
                    worldMap.getUniqueBattlePairs().add(battlePair);

                }
            }
        }
    }

}
