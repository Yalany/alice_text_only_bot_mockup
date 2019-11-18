package game;

import game.actions.ActionContext;
import game.actions.GameEvent;

import java.util.HashSet;

public final class Game {
    private final PlayerSessionCache contextCache = new PlayerSessionCache();
    private final HashSet<GameEvent> gameEvents = new HashSet<>();

    /**
     * @param request реквест, на который требуется ответ
     * @return ответ со всей информацией, необходимой для вывода ответа игроку
     */
    public GameResponse getResponse(final GameRequest request) {
        var actionContext = contextCache.getSession(request);
        var isCatchAll = true;
        for (GameEvent intent : gameEvents) {
            if (intent.run(request.getInput(), actionContext))
                isCatchAll = false;
        }
        if (isCatchAll)
            catchAll(actionContext);

        // todo заполнять response в соответствии с состоянием сессии
        return new GameResponse(request);
    }

    private void catchAll(final ActionContext context) {

    }

    void addGameEvent(final GameEvent event) {
        gameEvents.add(event);
    }
}
