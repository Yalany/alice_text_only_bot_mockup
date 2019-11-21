package game;

import com.google.gson.Gson;

class PlayerContextAccessor {
    private final static Gson GSON = new Gson();

    // todo вынести в конфиг
    private final static String USER_DATA_DIRECTORY = "../user_data/";
    private final static String FILE_POSTFIX = ".json";

    private static String path(final String userId) {
        return USER_DATA_DIRECTORY + userId + FILE_POSTFIX;
    }

    PlayerContext load(final String userId) {
        if (FileUtils.fileExists(path(userId)))
            return GSON.fromJson(FileUtils.readFile(path(userId)), PlayerContext.class);
        return new PlayerContext(userId);
    }

    void save(final PlayerContext context) {
        FileUtils.writeFile(path(context.getUserId()), GSON.toJson(context));
    }
}
