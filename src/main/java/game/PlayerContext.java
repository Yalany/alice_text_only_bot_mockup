package game;

import com.google.gson.annotations.SerializedName;
import game.actions.ActionContext;

final class PlayerContext implements ActionContext {
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

    PlayerContext(final String userId) {
        this.userId = userId;
    }

    String getUserId() {
        return userId;
    }

    @Override
    public GameResponse getResponse(final GameRequest request) {
        return new GameResponse(request);
    }
}
