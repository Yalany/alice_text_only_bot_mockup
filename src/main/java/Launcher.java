import com.sun.net.httpserver.HttpServer;
import game.Config;
import game.Game;
import game.Request;
import protocol.AliceRequest;

import java.io.IOException;
import java.net.InetSocketAddress;

public final class Launcher {
  private final static int PORT = 8000;
  private final static int BACKLOG = 0;
  private final static String CONTEXT_PATH = "/game";

  private final static Game GAME = new Game();

  public static void main(final String[] args) throws IOException {
    var server = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);
    server.createContext(CONTEXT_PATH, httpExchange -> {
      var request = new String(httpExchange.getRequestBody().readAllBytes());
      var response = getResponse(Config.GSON.fromJson(request, AliceRequest.class));
      httpExchange.sendResponseHeaders(200, response.getBytes().length);
      var responseBody = httpExchange.getResponseBody();
      responseBody.write(response.getBytes());
      responseBody.close();
    });
    server.start();
  }

  static String getResponse(final AliceRequest aliceRequest) {
    var gameRequest = new Request(aliceRequest);
    var gameResponse = GAME.getResponse(gameRequest);
    var aliceResponse = gameResponse.toAliceResponse();
    return Config.GSON.toJson(aliceResponse);
  }
}
