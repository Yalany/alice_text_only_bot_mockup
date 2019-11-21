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
        var inContext = contextCache.getContext(request);
        var isCatchAll = true;
        for (GameEvent event : gameEvents)
            if (event.run(request.getInput(), inContext))
                isCatchAll = false;

        if (isCatchAll) return catchAll(request);
        return inContext.getResponse(request);
    }

    void addGameEvent(final GameEvent event) {
        gameEvents.add(event);
    }

    private GameResponse catchAll(final GameRequest request) {
        return new GameResponse(request);
    }
}
