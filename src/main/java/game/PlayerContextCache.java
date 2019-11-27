package game;

import game.actions.ActionContext;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

final class PlayerContextCache {
    // todo вынести в конфиг
    private final static long TIMEOUT_SECONDS = 600;
    private final HashMap<String, ActionContext> cache = new HashMap<>();
    private final HashMap<String, Timer> timeouts = new HashMap<>();

    ActionContext getContext(final GameRequest request) {
         return request.isNewSession()
                 ? cacheContext(request.getUserId())
                 : getCachedContext(request.getUserId());
    }

    private ActionContext cacheContext(final String userId) {
        cache.put(userId, PlayerContext.Loader.load(userId));
        return getCachedContext(userId);
    }

    private ActionContext getCachedContext(final String userId) {
        assert cache.containsKey(userId) : "attempt to get non-cached context from cache";
        resetTimeout(userId);
        return cache.get(userId);
    }

    private void resetTimeout(final String userId) {
        if (timeouts.containsKey(userId))
            timeouts.remove(userId).cancel();
        var timer = new Timer();
        timeouts.put(userId, timer);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PlayerContext.Loader.save(cache.remove(userId));
                timeouts.remove(userId).cancel();
            }
        },1000 * TIMEOUT_SECONDS);
    }
}