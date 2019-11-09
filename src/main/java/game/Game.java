package game;

import game.actions.GameEvent;
import nlp.UserIntent;
import protocol.AliceResponse;

import java.util.HashMap;

public final class Game {
    private final PlayerSessionCache contextCache = new PlayerSessionCache();
    private final HashMap<UserIntent, GameEvent> gameEvents = new HashMap<>();

    /**
     * @param request реквест, на который требуется ответ
     * @return ответ со всей информацией, необходимой для вывода ответа игроку
     */
    public GameResponse getResponse(final GameRequest request) {
        var gameContext = contextCache.getSession(request);
        gameEvents.keySet().stream()
                .filter(intent -> intent.isIntended(request.getInput()))
                .forEach(intent -> gameEvents.get(intent).run(gameContext));

        // todo заполнять response в соответствии с состоянием сессии
        return new GameResponse(request)
                .setText(null)
                .setText("s")
                .setTTS(null)
                .setTTS("s")
                .setButtons(null)
                .setButtons(new AliceResponse.Response.Button[] {})
                .setEndSession(true)
                .setEndSession(false);
    }

    // эвент должен быть собран, то есть иметь все нужные подписки действий
    void addEventForIntent(final UserIntent intent, final GameEvent event) {
        gameEvents.put(intent, event);
    }
}
