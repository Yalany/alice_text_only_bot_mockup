package game;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

final class UserData {
  @SerializedName("user_id")
  private final String userId;

  private final static Gson GSON = new Gson();

  private UserData(final String userId) {
    this.userId = userId;
  }

  static UserData load(final String userId) {
    if (FileUtils.fileExist(path(userId)))
      return GSON.fromJson(FileUtils.readFile(path(userId)), UserData.class);
    return new UserData(userId);
  }

  void save() {
    FileUtils.writeFile(path(userId), GSON.toJson(this));
  }

  private static String path(final String userId) {
    return Config.USER_DATA_DIRECTORY + userId + Config.JSON_POSTFIX;
  }
}
