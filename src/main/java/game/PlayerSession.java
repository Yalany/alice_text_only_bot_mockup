package game;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import game.actions.ActionContext;

final class PlayerSession implements ActionContext {
    private final static String USER_DATA_DIRECTORY = "../user_data/";
    private final static String FILE_POSTFIX = ".json";
    private final static Gson GSON = new Gson();

    @SerializedName("user_id")
    private final String userId;

    @SerializedName("day")
    private int day;

    @SerializedName("day_part")
    private int dayPart;

    @SerializedName("energy")
    private int energy;

    @SerializedName("food")
    private int food;

    @SerializedName("health")
    private int health;

    private PlayerSession(final String userId) {
        this.userId = userId;
    }

    void save() {
        FileUtils.writeFile(path(userId), GSON.toJson(this));
    }

    static PlayerSession get(final String userId) {
        if (FileUtils.fileExists(path(userId)))
            return GSON.fromJson(FileUtils.readFile(path(userId)), PlayerSession.class);
        return new PlayerSession(userId);
    }

    private static String path(final String userId) {
        return USER_DATA_DIRECTORY + userId + FILE_POSTFIX;
    }
}
