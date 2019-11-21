package game;

import protocol.AliceResponse;

public final class GameResponse {
    private final AliceResponse response;

    GameResponse(final GameRequest input) {
        assert (input != null) : "GameRequest cannot be null";
        var request = input.getAliceRequest();
        response = new AliceResponse();
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

    GameResponse setButtons(final AliceResponse.Response.Button[] buttons) {
        response.response.buttons = buttons;
        return this;
    }

    GameResponse setEndSession(final boolean endSession) {
        response.response.endSession = endSession;
        return this;
    }

    public AliceResponse toAliceResponse() {
        return response;
    }
}
