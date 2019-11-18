package game.solution;

import game.actions.ActionContext;

import java.util.Random;

public class PlayerData implements ActionContext {
    private Random random = new Random();
    private GameMap gameMap;
    private boolean canOpenDoor = true;
    private boolean canRemoveObstacle = true;
    private boolean canRemoveSleeper = true;
    private boolean canRemoveEnemy = true;

    public boolean isCanOpenDoor() {
        return canOpenDoor;
    }

    public boolean isCanRemoveObstacle() {
        return canRemoveObstacle;
    }

    public boolean isCanRemoveSleeper() {
        return canRemoveSleeper;
    }

    public boolean isCanRemoveEnemy() {
        return canRemoveEnemy;
    }

    public void killRandomTeamMember() {
        if (random.nextBoolean() && canOpenDoor) {
            canOpenDoor = false;
            return;
        }
        if (random.nextBoolean() && canRemoveObstacle) {
            canOpenDoor = false;
            return;
        }
        if (random.nextBoolean() && canRemoveSleeper) {
            canOpenDoor = false;
            return;
        }
        if (random.nextBoolean() && canRemoveEnemy) {
            canOpenDoor = false;
        }
    }

    private class GameMap {
        private class MapNode {

        }

        private class MapEvent {

        }
    }
}
