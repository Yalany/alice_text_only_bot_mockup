package game;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

final class UserDataCache {
  private final HashMap<String, UserData> cache = new HashMap<>();
  private final HashMap<String, Timer> timeouts = new HashMap<>();

  void cacheUserData(final String userId, UserData userData) {
    cache.put(userId, userData);
    resetTimeout(userId);
  }

  UserData getUserData(final String userId) {
    assert cache.containsKey(userId) : "attempt to get non-cached UserData from UserDataCache";
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
