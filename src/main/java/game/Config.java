package game;

import com.google.gson.Gson;

public interface Config {
  long USER_DATA_CACHE_TIMEOUT_SECONDS = 600;
  String USER_DATA_DIRECTORY = "../user_data/";
  String JSON_POSTFIX = ".json";

  Gson GSON = new Gson();
}
