package game;

public final class Game {
  private final UserDataCache userDataCache = new UserDataCache();

  /**
   * @param request реквест, на который требуется ответ
   * @return ответ со всей информацией, необходимой для вывода ответа игроку
   */
  public Response getResponse(final Request request) {
    assert request != null : "request is null";
    var userData = getUserData(request.getUserId(), request.isNewSession());
    return new Response(request);
  }

  private UserData getUserData(final String userId, final boolean isNewSession) {
    if (isNewSession) userDataCache.cacheUserData(userId, UserData.getOrNew(userId));
    return userDataCache.getUserData(userId);
  }
}
