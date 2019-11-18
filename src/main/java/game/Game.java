package game;

import game.actions.GameEvent;

import java.util.HashSet;

public final class Game {
    private final PlayerContextCache contextCache = new PlayerContextCache();
    private final HashSet<GameEvent> gameEvents = new HashSet<>();

    /**
     * @param request реквест, на который требуется ответ
     * @return ответ со всей информацией, необходимой для вывода ответа игроку
     */
    public GameResponse getResponse(final GameRequest request) {
        var actionContext = contextCache.getContext(request);
        var isCatchAll = true;
        for (GameEvent intent : gameEvents)
            if (intent.run(request.getInput(), actionContext))
                isCatchAll = false;
        if (isCatchAll)
            actionContext.catchAll();

        // todo заполнять response в соответствии с состоянием контекста
        return new GameResponse(request);
    }


    void addGameEvent(final GameEvent event) {
        gameEvents.add(event);
    }
}
