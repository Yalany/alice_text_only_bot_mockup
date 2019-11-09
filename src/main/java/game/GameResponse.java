package game;

public final class GameResponse {
    private final protocol.AliceResponse response;

    GameResponse(final GameRequest input) {
        var request = input.getAliceRequest();
        response = new protocol.AliceResponse();
        response.response = response.new Response();
        response.session = response.new Session();
        response.session.messageId = request.session.messageId;
        response.session.sessionId = request.session.sessionId;
        response.session.userId = request.session.userId;
        response.version = request.version;
    }

    GameResponse setText(final String text) {
        response.response.text = text;
        return this;
    }

    GameResponse setTTS(final String tts) {
        response.response.tts = tts;
        return this;
    }

    GameResponse setButtons(final protocol.AliceResponse.Response.Button[] buttons) {
        response.response.buttons = buttons;
        return this;
    }

    GameResponse setEndSession(final boolean endSession) {
        response.response.endSession = endSession;
        return this;
    }

    public protocol.AliceResponse toAliceResponse() {
        return response;
    }
}
