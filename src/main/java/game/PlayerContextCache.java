package game;

import com.google.gson.Gson;
import game.actions.ActionContext;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

final class PlayerContextCache {
    // todo вынести в конфиг
    private final static long TIMEOUT_SECONDS = 600;
    private final static String USER_DATA_DIRECTORY = "../user_data/";
    private final static String FILE_POSTFIX = ".json";
    private final HashMap<String, ActionContext> cache = new HashMap<>();
    private final HashMap<String, Timer> timeouts = new HashMap<>();

    ActionContext getContext(final GameRequest request) {
         return request.isNewSession()
                 ? cacheContext(request.getUserId())
                 : getCachedContext(request.getUserId());
    }

    private ActionContext cacheContext(final String userId) {
        cache.put(userId, PlayerContextAccessor.load(userId));
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
                PlayerContextAccessor.save(cache.remove(userId));
                timeouts.remove(userId).cancel();
            }
        },1000 * TIMEOUT_SECONDS);
    }

    private final static class PlayerContextAccessor {
        private final static Gson GSON = new Gson();

        private static ActionContext load(final String userId) {
            if (FileUtils.fileExists(path(userId)))
                return GSON.fromJson(FileUtils.readFile(path(userId)), PlayerContext.class);
            return new PlayerContext(userId);
        }

        private static void save(final ActionContext context) {
            FileUtils.writeFile(path(context.getUserId()), GSON.toJson(context));
        }

        private static String path(final String userId) {
            return USER_DATA_DIRECTORY + userId + FILE_POSTFIX;
        }
    }
}