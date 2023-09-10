package org.example.map.manager;

import org.example.elements.Element;
import org.example.map.WorldMap;
import org.example.utils.UniquePair;

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
