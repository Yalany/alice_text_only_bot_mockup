package game;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import game.actions.ActionContext;

final class PlayerContext implements ActionContext {
    private final static Gson GSON = new Gson();

    // todo вынести в конфиг
    private final static String USER_DATA_DIRECTORY = "../user_data/";
    private final static String FILE_POSTFIX = ".json";

    // todo вынести в конфиг
    private final static int STARTING_FOOD = 20;
    private final static int STARTING_ENERGY = 70;
    private final static int STARTING_HEALTH = 50;

    @SerializedName("user_id")
    private final String userId;

    @SerializedName("day")
    private int day = 0;

    @SerializedName("day_part")
    private int dayPart = 0;

    @SerializedName("energy")
    private int energy = STARTING_ENERGY;

    @SerializedName("food")
    private int food = STARTING_FOOD;

    @SerializedName("health")
    private int health = STARTING_HEALTH;

    private PlayerContext(final String userId) {
        this.userId = userId;
    }

    static PlayerContext get(final String userId) {
        if (FileUtils.fileExists(path(userId)))
            return GSON.fromJson(FileUtils.readFile(path(userId)), PlayerContext.class);
        return new PlayerContext(userId);
    }

    @Override
    public GameResponse getResponse(final GameRequest request) {
        return new GameResponse(request);
    }

    public void save() {
        FileUtils.writeFile(path(userId), GSON.toJson(this));
    }

    private static String path(final String userId) {
        return USER_DATA_DIRECTORY + userId + FILE_POSTFIX;
    }
}
