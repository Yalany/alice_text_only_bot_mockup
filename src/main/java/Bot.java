import com.google.gson.Gson;
import game.Game;
import game.Request;
import protocol.AliceRequest;

final class Bot {
    private final static Gson gson = new Gson();
    private final Game game = new Game();

    /**
     * @param json request in form of JSON string
     * @return response in form of JSON string
     */
    String ask(final String json) {
        var aliceRequest = gson.fromJson(json, AliceRequest.class);
        var gameRequest = new Request(aliceRequest);
        var gameResponse = game.getResponse(gameRequest);
        var aliceResponse = gameResponse.toAliceResponse();
        return gson.toJson(aliceResponse);
    }
}
