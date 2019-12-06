package game;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import game.actions.ActionContext;

final class UserData implements ActionContext {
    @SerializedName("user_id")
    private final String userId;

    private final static Gson GSON = new Gson();


    ///////////////////////////////////////////////
    // Below lies serialization behavior
    ///////////////////////////////////////////////

    private UserData(final String userId) {
        this.userId = userId;
    }

    // todo посмотреть как линкуются аккаунты
    static UserData load(final String userId) {
        if (FileUtils.fileExists(path(userId)))
            return GSON.fromJson(FileUtils.readFile(path(userId)), UserData.class);
        return new UserData(userId);
    }

    private static void save(final UserData userData) {
        FileUtils.writeFile(path(userData.userId), GSON.toJson(userData));
    }

    private static String path(final String userId) {
        return Settings.USER_DATA_DIRECTORY + userId + Settings.JSON_POSTFIX;
    }

    void save() {
        save(this);
    }
}
