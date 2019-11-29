package game;

import game.actions.ActionContext;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

final class PlayerContextCache {
    private final HashMap<String, ActionContext> cache = new HashMap<>();
    private final HashMap<String, Timer> timeouts = new HashMap<>();

    ActionContext getContext(final GameRequest request) {
        if (request.isNewSession()) cacheContext(request.getUserId());
        return getCachedContext(request.getUserId());
    }

    private void cacheContext(final String userId) {
        cache.put(userId, PlayerContext.Loader.load(userId));
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
                cache.remove(userId).save();
                timeouts.remove(userId).cancel();
            }
        }, 1000 * Config.USER_DATA_CACHE_TIMEOUT_SECONDS);
    }
}