package game;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

final class PlayerSessionCache {
    private final static long TIMEOUT_SECONDS = 600;

    private final HashMap<String, PlayerSession> cache = new HashMap<>();
    private final HashMap<String, Timer> timeouts = new HashMap<>();

    PlayerSession getSession(final GameRequest request) {
         return request.isNewSession()
                 ? cacheSession(request.getUserId())
                 : getCachedSession(request.getUserId());
    }

    private PlayerSession cacheSession(final String userId) {
        cache.put(userId, PlayerSession.get(userId));
        return getCachedSession(userId);
    }

    private PlayerSession getCachedSession(final String userId) {
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
            public void run() {
                cache.remove(userId);
                timeouts.remove(userId).cancel();
            }
        },1000 * TIMEOUT_SECONDS);
    }
}
