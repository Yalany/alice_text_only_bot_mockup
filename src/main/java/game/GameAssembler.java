package game;

import com.google.gson.Gson;
import game.actions.Action;
import game.actions.ActionQueue;
import game.actions.Condition;
import game.actions.GameEvent;
import nlp.UserIntent;

public class GameAssembler {
    private final static Gson GSON = new Gson();
    /**
     * по порядку, как собирается игра:
     * - собираются все возможные в игре Actions
     * - из них собираются ActionQueue
     * - к этим ActionQueue крепятся Conditions
     * - создаётся UserIntent, который будет запускать событие
     * - создаётся GameEvent
     * - На GameEvent подписываются все Actions с Conditions, которые могут им вызываться
     */

    private final static String USER_DATA_DIRECTORY = "../intents/";
    private final static String FILE_POSTFIX = ".json";

    private static String path(final String filename) {
        return USER_DATA_DIRECTORY + filename + FILE_POSTFIX;
    }

    private static UserIntent readIntentFromFile(final String intentFilename) {
        if (FileUtils.fileExists(path(intentFilename)))
            return GSON.fromJson(FileUtils.readFile(path(intentFilename)), UserIntent.class);
        throw new IllegalArgumentException("no such intent:\"" + path(intentFilename) + "\"");
    }

    private static GameEvent assembleEvent(final UserIntent intent, final Condition condition, final Action... actions) {
        var resultEvent = new GameEvent(intent);
        var actionsQueue = ActionQueue.newHead();
        for (var action : actions)
            actionsQueue.add(action);
        resultEvent.subscribe(condition, actionsQueue);
        return resultEvent;
    }

    private static GameEvent newSessionEvent(final String intentFilename) {
        assert FileUtils.fileExists(intentFilename) : "no such file for intent: " + intentFilename;
        return assembleEvent(readIntentFromFile(intentFilename),
                context -> true,
                context -> {
                },
                context -> {
                });
    }

    public Game assembleGame() {
        var game = new Game();
        game.addGameEvent(newSessionEvent("default_intent"));
        game.addGameEvent(newSessionEvent(""));

        return game;
    }
}
