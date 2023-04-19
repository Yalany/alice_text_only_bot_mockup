import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public final class Launcher {
  private final static int PORT = 8000;
  private final static int BACKLOG = 0;
  private final static String CONTEXT_PATH = "/game";

  public static void main(final String[] args) throws IOException {
    var server = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);
    var bot = new Bot();
    server.createContext(CONTEXT_PATH, httpExchange -> {
      var response = bot.ask(new String(httpExchange.getRequestBody().readAllBytes()));
      httpExchange.sendResponseHeaders(200, response.getBytes().length);
      var responseBody = httpExchange.getResponseBody();
      responseBody.write(response.getBytes());
      responseBody.close();
    });
    server.start();
  }
}
