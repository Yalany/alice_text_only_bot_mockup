package game.actions;

import game.GameRequest;
import game.GameResponse;

public interface ActionContext {
    GameResponse getResponse(GameRequest request);
}
