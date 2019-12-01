package game;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import game.actions.ActionContext;

final class PlayerContext implements ActionContext {
    @SerializedName("user_id")
    private final String userId;

    private PlayerContext(final String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void save() {
        Loader.save(this);
    }

    @Override
    public GameResponse getResponse(final GameRequest request) {
        return new GameResponse(request);
    }

    final static class Loader {
        private final static Gson GSON = new Gson();

        static ActionContext load(final String userId) {
            if (FileUtils.fileExists(path(userId)))
                return GSON.fromJson(FileUtils.readFile(path(userId)), PlayerContext.class);
            return new PlayerContext(userId);
        }

        private static void save(final ActionContext context) {
            FileUtils.writeFile(path(context.getUserId()), GSON.toJson(context));
        }

        private static String path(final String userId) {
            return GameSettings.USER_DATA_DIRECTORY + userId + GameSettings.JSON_POSTFIX;
        }
    }
}
